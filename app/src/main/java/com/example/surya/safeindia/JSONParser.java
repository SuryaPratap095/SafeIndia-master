package com.example.surya.safeindia;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.UnsupportedAddressTypeException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by surya on 22/8/16.
 */
public class JSONParser {

    static InputStream is=null;
    static JSONObject jobj=null;
    static String JSON="";

    public JSONParser(String json){
        this.JSON=json;
    }

    public String sendPostRequest(String requestURL, HashMap<String,String> postDataParams){
        URL url;

        StringBuilder sb=new StringBuilder();

        try{
            url=new URL(requestURL);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);

            OutputStream outputStream=connection.getOutputStream();

            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

            writer.flush();
            writer.close();
            outputStream.close();

            int responseCode=connection.getResponseCode();

            if(responseCode==HttpURLConnection.HTTP_OK){
                BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                sb=new StringBuilder();
                String response;

                while (br.readLine()!=null){
                    response=br.readLine();
                    sb.append(br.readLine());
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return sb.toString();
    }

    public String sendGetRequest(String requestURL){
        StringBuilder sb=new StringBuilder();
        try{
            URL url=new URL(requestURL);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String response;

            while(bufferedReader.readLine()!=null){
                response=bufferedReader.readLine();
                sb.append(response+"\n");
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return sb.toString();
    }

    public String getRequestParams(String requestURL,String id){
        StringBuilder sb=new StringBuilder();
        try{
            URL url=new URL(requestURL+id);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response;

            while(bufferedReader.readLine()!=null){
                response=bufferedReader.readLine();
                sb.append(response+"\n");
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return sb.toString();

    }

    public String getPostDataString(HashMap<String,String> params) throws UnsupportedEncodingException{

        StringBuilder sb=new StringBuilder();
        boolean first=true;
        for(Map.Entry<String,String> entry:params.entrySet()){
            if(first)
                first=false;
            else
                sb.append("&");

            sb.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
        }

        return sb.toString();
    }
}
