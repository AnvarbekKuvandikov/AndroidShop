package com.example.shop;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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



    public Integer makeServiceAddNewProduct(String reqUrl, Product selectedItem){
        HttpURLConnection conn= null;
        try {
        URL url=new URL(reqUrl);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            JSONObject object=new JSONObject();
            try {
                object.put("id",selectedItem.getPutId());
                object.put("name",selectedItem.getName());
                object.put("count",selectedItem.getCount());
                object.put("incount",selectedItem.getIncount());
                object.put("price",selectedItem.getPrice());
                object.put("inprice",selectedItem.getInprice());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            String jsonInputString=object.toString();
            OutputStream os=conn.getOutputStream();
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader( new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return Integer.parseInt(response.toString());



        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }





    public Integer makeServiceAddProduct(String reqUrl, Product selectedItem){
        HttpURLConnection conn= null;
        try {
        URL url=new URL(reqUrl);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            JSONObject object=new JSONObject();
            try {
                object.put("id",selectedItem.getPutId());
                object.put("productId",selectedItem.getId());
                object.put("name",selectedItem.getName());
                object.put("count",selectedItem.getCount());
                object.put("incount",selectedItem.getIncount());
                object.put("price",selectedItem.getPrice());
                object.put("inprice",selectedItem.getInprice());
                object.put("incnt",selectedItem.getIncnt());
                /*{
                    "id": 3407,
                        "productId": 1088,
                        "nameShort": null,
                        "name": "1091. Фенал 1820 ",
                        "count":  10,
                        "incount": 9,
                        "price": 20000,
                        "inprice": 0,
                        "shtrix": "8803465418203",
                        "incnt": 12
                }*/
                Log.v("Http",selectedItem.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String jsonInputString=object.toString();
            OutputStream os=conn.getOutputStream();
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader( new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return Integer.parseInt(response.toString());



        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void putProduct(String reqUrl, Product selectedItem) {

        HttpURLConnection conn= null;
        try {
            URL url=new URL(reqUrl);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            JSONObject object=new JSONObject();
            try {
                object.put("id",selectedItem.getPutId());
                object.put("productId",selectedItem.getId());
                object.put("name",selectedItem.getName());
                object.put("count",selectedItem.getCount());
                object.put("incount",selectedItem.getIncount());
                object.put("price",selectedItem.getPrice());
                object.put("inprice",selectedItem.getInprice());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            String jsonInputString=object.toString();
            OutputStream os=conn.getOutputStream();
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader( new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void makeServiceDelItem(String reqUrl){
        String response="0";
        HttpURLConnection conn= null;
        URL url= null;

        try {
            url = new URL(reqUrl);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);

            if(conn.getResponseCode() == 204){
                Log.d(TAG,"Deleted");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Void makeServiceBlockAsos(String reqUrl,Integer asosId){
        String response=null;
        try{
            URL url=new URL(reqUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            JSONObject object=new JSONObject();
            try {
                object.put("id",asosId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(object.toString());
            writer.flush();
            writer.close();
            os.close();
            conn.connect();
            Log.v("MyLog4",conn.getResponseMessage()+"");

        } catch (ProtocolException e) {
            Log.v(TAG,"ProtocolExceptio: "+e.getMessage());
        } catch (MalformedURLException e) {
            Log.v(TAG,"MalformedURLException: "+e.getMessage());
        } catch (IOException e) {
            Log.v(TAG+"4","IOException: "+e.getMessage());
        }
        return null;

    }




    public  String makeServiceCall(String reqUrl){

        String response=null;
        try{
            URL url=new URL(reqUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in=new BufferedInputStream(conn.getInputStream());
            response=convertStreamToString(in);
            conn.disconnect();
        } catch (ProtocolException e) {
            Log.v(TAG,"ProtocolExceptio: "+e.getMessage());
        } catch (MalformedURLException e) {
            Log.v(TAG,"MalformedURLException: "+e.getMessage());
        } catch (IOException e) {
            Log.v(TAG,"IOException: "+e.getMessage());
        }
        return response;
    }




    public String makeServiceCreateAsos(String reqUrl, User user, Integer haridorId, Integer type){
        String response=null;
        try{
            URL url=new URL(reqUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            JSONObject jsonParam = new JSONObject();
            /* {
                "clientId": 4,
                "userId": 99,
                "xodimId": 99,
                "haridorId":
                "dilerId":0,
                "turOper": 2



        }*/

            jsonParam.put("clientId", user.getClientId());
            jsonParam.put("userId", user.getId());
            jsonParam.put("xodimId", user.getId());
            jsonParam.put("haridorId",haridorId);
            jsonParam.put("dilerId", 0);
            jsonParam.put("turOper", 2);
            jsonParam.put("sotuvTuri", type);



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
