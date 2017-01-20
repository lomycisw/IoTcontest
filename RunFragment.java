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

import static android.R.attr.id;
import static android.R.attr.start;

/**
 * Created by f-masa on 2016/12/21.
 */

public class RunFragment extends Fragment {
    private OnFragmentInteractionListener fragmentInteractionListener;

    public RunFragment(){}
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_run,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intent = new Intent(getActivity(),LocationService.class);
        getActivity().startService(intent);

    }



    @Override
    public void onAttach(Activity activity) {
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
        getActivity().stopService(intent);
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
