package com.hackathon.dadbod.dadbod;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by iowaf on 10/21/2017.
 */

public class FragmentSignUp extends Fragment {

    public FragmentSignUp(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        return v;
    }
}
