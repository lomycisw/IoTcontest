package com.example.f_masa.proximityareadetection;

/**
 * Created by f-masa on 2016/12/27.
 */

public interface AsyncCallback {
    void onPreExecute();
    void onPostExecute(AsyncHttp.HttpResponse result);
    void onProgressUpdate(int progress);
    void onCancelled();

}
