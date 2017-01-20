package com.example.f_masa.proximityareadetection;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity implements View.OnClickListener{
    private Intent authActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViewById(R.id.authbutton).setOnClickListener(this);
        authActivity = new Intent(this,AuthActivity.class);

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.authbutton:
                startActivity(authActivity);
                finish();
                break;
        }

    }
}
