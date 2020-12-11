package com.example.anecdotru;

public class ExampleList {
    private int mImageResourse;
    private String mText1;

    public ExampleList(int imageResourse, String text1){
        mImageResourse = imageResourse;
        mText1 = text1;
    }

    public int getImageResourse() {
        return mImageResourse;
    }

    public String getText1(){
        return mText1;
    }
}
