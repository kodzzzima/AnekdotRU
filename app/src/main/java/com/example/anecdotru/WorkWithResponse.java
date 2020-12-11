package com.example.anecdotru;

import java.util.regex.Pattern;

public class WorkWithResponse {
    public String mResponse;
    public String[] splitedString;
    public String normalText;
    public String replacedText;
    public String finalTextMassive[];
    final char dm = (char) 34;
    public String separator = "\\";

    public WorkWithResponse( String response ){
        mResponse = response;
    }

    public void getNormalResponse(){
        splitedString = mResponse.split(";");

        normalText = splitedString[1];
        normalText = normalText.substring(36);

        replacedText = normalText.replaceAll(Pattern.quote(separator),"\\\\").replaceAll("<br>","\n");

        finalTextMassive = replacedText.split("\\\\\",\\\\\"");
        mResponse = finalTextMassive[0];

    }

    public String getmResponse() {
        return mResponse;
    }

    public String[] getArrayResponse(){
        return finalTextMassive;
    }
}
