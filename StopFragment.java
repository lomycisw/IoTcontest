package com.example.f_masa.proximityareadetection;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.zip.Inflater;

import static android.R.attr.id;
import static android.R.attr.x;

/**
 * Created by f-masa on 2016/12/21.
 */

public class StopFragment extends Fragment{
    private OnFragmentInteractionListener fragmentInteractionListener;

    public StopFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_stop,container,false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.sendlocationdata).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(fragmentInteractionListener != null)
                    fragmentInteractionListener.onFragmentInteraction(new RunFragment());

            }
        });
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        if(activity instanceof OnFragmentInteractionListener){
            fragmentInteractionListener = (OnFragmentInteractionListener)activity;
        }
        else{
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentInteractionListener = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

}
