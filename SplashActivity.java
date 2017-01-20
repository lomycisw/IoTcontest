package com.example.f_masa.proximityareadetection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.f_masa.proximityareadetection.utils.SettingUtils;

/**
 * Created by f-masa on 2017/01/11.
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(Authenticator.getInstance().isAuth()){
            Authenticator.getInstance().setOnCallBack(new Authenticator.CallBackTask(){
                @Override
                public void CallBack(String result) {
                    super.CallBack(result);
                    if(Authenticator.getInstance().isAuth()) {
                        SettingUtils.preferenceRegister(SplashActivity.this);
                        Intent intent = new Intent(SplashActivity.this, LocationActivity.class);
                        SplashActivity.this.startActivity(intent);
                        SplashActivity.this.finish();
                    }
                }
            });
            Authenticator.getInstance().Authorization(
                    Authenticator.getInstance().getCode(),
                    Authenticator.getInstance().getSecret(),
                    Authenticator.getInstance().getUuid()
            );
        }else {
            startActivity(new Intent(this,MainActivity.class));
        }

        SplashActivity.this.finish();

    }
}
