package com.example.telmex.assignment2.fragmentLayout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.telmex.assignment2.R;
import com.example.telmex.assignment2.model.dataAdapter;


public class Classic_Music extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // dataAdapter process = new dataAdapter();
        //process.onCreateViewHolder()

        return inflater.inflate(R.layout.fragment_classic__music, container, false);
       // return inflater.inflate(R.layout.fragment_classic__music, container, false);
    }


}
