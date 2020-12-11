package com.example.anecdotru;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {
    private static final String ENCODING ="cp1251";
    private static final String ANECDOT_URL = "https://www.anekdot.ru/rss/random.html";

    public static URL generateURL(){

        Uri builtUrl = Uri.parse(ANECDOT_URL).buildUpon().build();
        URL url = null;
        try {
            url = new URL(builtUrl.toString());
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }
    public static String responseFromURL(URL url) throws IOException{
        String query = url.toString();
        HttpURLConnection connection = null;
        StringBuffer sb = null;
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(250);
            connection.setReadTimeout(250);

            connection.connect();

            sb = new StringBuffer();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), ENCODING));
                String line;
                while ((line = in.readLine()) != null){
                    sb.append(line);
                    sb.append("\n");
                }
            }
            else{
                Log.d("yy","fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if ( connection != null){
                connection.disconnect();
            }
        }

        return sb.toString();
    }
}
