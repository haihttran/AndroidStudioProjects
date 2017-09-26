package com.example.haitran.geoquiz;

/**
 * Created by Hai Tran on 2/23/2017.
 */

public class Question {

    private int mTestResId;
    private boolean mAnswerTrue;

    public int getTestResId() {
        return mTestResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setTestResId(int testResId) {
        mTestResId = testResId;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public Question(int textResId, boolean answerTrue){
        mTestResId = textResId;
        mAnswerTrue = answerTrue;
    }
}
