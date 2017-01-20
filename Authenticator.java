package com.example.f_masa.proximityareadetection;

import java.net.HttpURLConnection;
import com.example.f_masa.proximityareadetection.AsyncHttp.HttpResponse;
import com.example.f_masa.proximityareadetection.utils.JsonUtils;
import com.example.f_masa.proximityareadetection.utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by f-masa on 2016/12/19.
 */

public class Authenticator {


    public static Authenticator getInstance(){return authenticator;}
    private Authenticator(){}
    private static Authenticator authenticator = new Authenticator();


    private String code = "000";
    private String secret = "005fd4cb-cec2-44b3-9757-7b242aab02a2A";
    private String uuid = "";
    private String device_code = "";
    private String token = "";
    private boolean auth = false;


    public CallBackTask callbacktask;

    public void setOnCallBack(CallBackTask _cbj) {
        callbacktask = _cbj;
    }
    public static class CallBackTask {
        public void CallBack(String result) {

        }
    }

    public void sendAccessToken(){
        AsyncHttp asyncHttp = new AsyncHttp(new AsyncCallback() {
            @Override
            public void onPreExecute() {
            }
            @Override
            public void onPostExecute(HttpResponse result) {
                if(!(result.status == HttpURLConnection.HTTP_UNAUTHORIZED || result.status == -1)){
                    auth = true;
                    callbacktask.CallBack(result.body);
                }else {
                    reject();
                }
            }
            @Override
            public void onProgressUpdate(int progress) {
            }
            @Override
            public void onCancelled() {
            }
        });
        asyncHttp.execute("POST", NetworkUtils.authUrl, JsonUtils.OAuthJson());
    }

    public void Authorization(String code, String secret, String uuid){
        Authenticator.getInstance().setUuid(uuid);
        Authenticator.getInstance().setSecret(secret);
        Authenticator.getInstance().setCode(code);

        AsyncHttp asyncHttp = new AsyncHttp(new AsyncCallback() {
            @Override
            public void onPreExecute() {
            }
            @Override
            public void onPostExecute(HttpResponse result) {
                try{
                    JSONObject json = new JSONObject(result.body);
                    Authenticator.getInstance().setDevice_code(json.getString("device_code"));
                    Authenticator.getInstance().setToken(json.getString("token"));
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                sendAccessToken();

            }
            @Override
            public void onProgressUpdate(int progress) {}

            @Override
            public void onCancelled() {}
        });


        asyncHttp.execute("POST",NetworkUtils.registerUrl,JsonUtils.RegisterJson());


        return;
    }


    public String getUuid(){return uuid;}
    public String getSecret() {return secret;}
    public String getCode() {return code;}
    public String getDevice_code() {return device_code;}
    public String getToken() {return token;}

    public void setUuid(String uuid) {this.uuid = uuid;}
    public void setSecret(String secret) {this.secret = secret; }
    public void setCode (String code){this.code = code;}
    public void setDevice_code(String device_code){this.device_code = device_code;}
    public void setToken (String token) {this.token = token ; }

    public boolean isAuth(){return auth;}
    public void setAuth(boolean bool){auth = bool;}
    public void reject() {auth = false;}


}
