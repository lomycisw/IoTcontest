package com.example.f_masa.proximityareadetection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.f_masa.proximityareadetection.utils.SettingUtils;

import java.util.regex.Pattern;


/**
 * Created by f-masa on 2016/12/19.
 */

public class AuthActivity extends Activity implements View.OnClickListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        findViewById(R.id.send).setOnClickListener(this);
        EditText codeText = (EditText)findViewById(R.id.code);
        EditText secretText = (EditText)findViewById(R.id.secret);
        EditText uuidText = (EditText)findViewById(R.id.uuid);
        codeText.setText(Authenticator.getInstance().getCode());
        secretText.setText(Authenticator.getInstance().getSecret());
        uuidText.setText(Authenticator.getInstance().getUuid());

        codeText.setFocusable(false);
        secretText.setFocusable(false);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.send:
                stateLocationActivity();
                break;

        }
    }

    private void stateLocationActivity(){
        String code   = ((EditText)findViewById(R.id.code)).getText().toString().trim();
        String secret = ((EditText)findViewById(R.id.secret)).getText().toString().trim();
        String uuid   = ((EditText)findViewById(R.id.uuid)).getText().toString().trim();

        Authenticator.getInstance().setOnCallBack(new Authenticator.CallBackTask() {
            @Override
            public void CallBack(String result) {
                super.CallBack(result);
                if (Authenticator.getInstance().isAuth()) {
                    SettingUtils.preferenceRegister(AuthActivity.this);

                    Intent intent = new Intent(AuthActivity.this, LocationActivity.class);
                    AuthActivity.this.startActivity(intent);
                    AuthActivity.this.finish();
                }
            }
        });

        // validate
        if(Pattern.compile("^[a-z0-9]*$").matcher(uuid).find()) {
            Authenticator.getInstance().Authorization(code,secret,uuid);
        }else {
            Toast.makeText(this, "車両NOは半角英数字にしてください", Toast.LENGTH_LONG).show();
        }
    }



}
