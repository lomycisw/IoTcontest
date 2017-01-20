package com.example.f_masa.proximityareadetection;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.example.f_masa.proximityareadetection.AsyncHttp.HttpResponse;


/**
 * Created by f-masa on 2016/12/27.
 */

public class AsyncHttp extends AsyncTask<String,Integer,HttpResponse> {
    public class HttpResponse {
        String header;
        String body;
        int status;

        HttpResponse(String header,String body,int status){
            this.header = header;
            this.body   = body;
            this.status = status;
        }
    }
    private AsyncCallback asyncCallback = null;

    public AsyncHttp(AsyncCallback asyncCallback){
        this.asyncCallback = asyncCallback;
    }
    private CallBackJson callbackjson;
    private String json = "sample";


    @Override
    protected HttpResponse doInBackground(String... strings) {
        String method = strings[0];
        String url = strings[1];
        this.json = strings[2];

        HttpResponse response = null;
        String requestProperty = "application/json; charset=utf-8";
        try {
            response = HttpConnect(method,new URL(url),requestProperty);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    protected void onPreExecute(){
        super.onPreExecute();
        this.asyncCallback.onPreExecute();
    }

    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        this.asyncCallback.onProgressUpdate(values[0]);
    }

    protected void onPostExecute(HttpResponse result){
        super.onPostExecute(result);
        this.asyncCallback.onPostExecute(result);

    }

    protected void onCancelled(){
        super.onCancelled();
        this.asyncCallback.onCancelled();

    }



    private HttpResponse HttpConnect(String method,URL url,String requestProperty) throws IOException {
        String buffer = "";
        HttpURLConnection http = (HttpURLConnection)url.openConnection();

        switch (method){
            case "POST":
                http.setRequestMethod(method);
                http.setDoOutput(true);
                http.setDoInput(true);
                http.setRequestProperty("Context-Type",requestProperty);
                OutputStream os = http.getOutputStream();
                PrintStream ps = new PrintStream(os);
                ps.print(json);
                ps.close();

                break;

            case "GET":
                http.setRequestMethod(method);
                http.connect();

                break;
        }
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
        buffer = reader.readLine();

        http.disconnect();

        HttpResponse response = new HttpResponse(null,buffer,http.getResponseCode());

        return response;
    }
}
