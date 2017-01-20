package com.example.f_masa.proximityareadetection;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import static android.R.attr.id;


/**
 * Created by f-masa on 2016/12/21.
 */

public class LocationActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private FragmentManager fragmentManager = getFragmentManager();
    private FragmentTransaction fragmentTransaction;
    public boolean fragmentState = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        setTitle( "" );

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Disable Back key
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onFragmentInteraction(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentState = !fragmentState;
        invalidateOptionsMenu();
        fragmentTransaction.replace(R.id.location_fragment,fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gps,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem stopItem = (MenuItem)menu.findItem(R.id.stop_settings);
        MenuItem logoutItem = (MenuItem)menu.findItem(R.id.logout);
        stopItem.setVisible(fragmentState);
        logoutItem.setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        invalidateOptionsMenu();
        switch (item.getItemId()){
            case R.id.logout:
                startActivity(new Intent(this,AuthActivity.class));
                finish();
                break;
            case R.id.stop_settings:
                onFragmentInteraction(new StopFragment());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
