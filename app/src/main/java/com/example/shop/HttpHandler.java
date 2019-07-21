package com.example.shop;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandler {
    private static String TAG="MyHttp";
    private static String url="http://";

    public HttpHandler() {

    }


    public String makeServiceCallPost(String reqUrl,User user){
        String response=null;
        try{
            URL url=new URL(reqUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("username", user.getUsername());
            jsonParam.put("userpass", user.getUserpass());


            conn.setConnectTimeout(15000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();
            OutputStream os =conn.getOutputStream();
            os.write(jsonParam.toString().getBytes());
            os.flush();
            os.close();

            InputStream in=new BufferedInputStream(conn.getInputStream());
            response=convertStreamToString(in);
            conn.disconnect();
        } catch (ProtocolException e) {
            Log.v(TAG,"ProtocolExceptio: "+e.getMessage());
        } catch (MalformedURLException e) {
            Log.v(TAG,"MalformedURLException: "+e.getMessage());
        } catch (IOException e) {
            Log.v(TAG,"IOException: "+e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;

    }


    public  String makeServiceCall(String reqUrl){

        String response=null;
        try{
            URL url=new URL(reqUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in=new BufferedInputStream(conn.getInputStream());
            response=convertStreamToString(in);
//            conn.disconnect();
        } catch (ProtocolException e) {
            Log.v(TAG,"ProtocolExceptio: "+e.getMessage());
        } catch (MalformedURLException e) {
            Log.v(TAG,"MalformedURLException: "+e.getMessage());
        } catch (IOException e) {
            Log.v(TAG,"IOException: "+e.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream in) {
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        StringBuilder stringBuilder=new StringBuilder();
        String line;
        try{
            while ((line=reader.readLine())!=null)
                stringBuilder.append(line).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }


}
