package com.busefisensi.efisiensiku.Fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busefisensi.efisiensiku.R;

public class BantuanFragment extends Fragment {

    public static BantuanFragment newInstance(){
        BantuanFragment fragment = new BantuanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_bantuan, container, false);
    }
}
