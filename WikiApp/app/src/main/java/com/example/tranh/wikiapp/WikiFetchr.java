package com.example.tranh.wikiapp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by tranh on 9/27/2017.
 */

public class WikiFetchr {

    public static final String KEY = "f3411501135d4f0686384ae6de9ae32b";

    public byte[] getUrlBytes(String urlSpec) throws IOException{
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " + urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        }finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException{
        return new String(getUrlBytes(urlSpec));
    }

    //Fetch WIkipedia page.
    public List<ArticleItem> fetchItems(){

        List<ArticleItem> items = new ArrayList<>();

        try{
            String url = Uri.parse("https://newsapi.org/v1/articles")
                    .buildUpon()
                    .appendQueryParameter("apiKey",KEY)
                    .appendQueryParameter("source","bbc-news")
                    .appendQueryParameter("sortBy","top")
                    /*.appendQueryParameter("generator","random")
                    .appendQueryParameter("prop","revisions|images")
                    .appendQueryParameter("rvprop","content")*/
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);
        } catch (JSONException je){
            Log.e(TAG, "Failed to parse JSON", je);
        } catch (IOException ioe){
            Log.e(TAG, "Failed to fetch items", ioe);
        }
        return items;
    }

    private void parseItems(List<ArticleItem> items, JSONObject jsonBody)
        throws IOException, JSONException{

        //JSONObject articlesJsonObject = jsonBody.getJSONObject("articles");
        JSONArray articleJsonArray = new JSONArray();
//        articleJsonArray.put(articlesJsonObject);
        articleJsonArray = jsonBody.getJSONArray("articles");
        for (int i = 0; i < articleJsonArray.length(); i++) {
            JSONObject articleJsonObject = articleJsonArray.getJSONObject(i);
            ArticleItem item = new ArticleItem();
            item.setmAuthor(articleJsonObject.getString("author"));
            item.setmTitle(articleJsonObject.getString("title"));
            if (!articleJsonObject.has("url")) {
                continue;
            }
            item.setmUrl(articleJsonObject.getString("url"));
            if (!articleJsonObject.has("urlToImage")) {
                continue;
            }
            item.setmImageUrl(articleJsonObject.getString("urlToImage"));
            items.add(item);
        }
    }
}
