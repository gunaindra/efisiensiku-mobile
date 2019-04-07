package com.busefisensi.efisiensiku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.model.Seat;
import com.busefisensi.efisiensiku.parser.Parser;
import com.busefisensi.efisiensiku.transport.HTTPClient;
import com.javasoul.swframework.component.SWLog;
import com.javasoul.swframework.component.SWProgressDialog;
import com.javasoul.swframework.component.SWToast;
import com.javasoul.swframework.model.SWResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BookingSeatActivity extends AppCompatActivity {

    private List<Seat> seats = new ArrayList<>();
    private Seat seat = new Seat();

    @Override
    public void onCreate(@Nullable  Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_seat);
        Intent intent = getIntent();
        String agenOrigin = intent.getStringExtra("agenOrigin");
        Log.d("agenOrigin", "hasilnya" + agenOrigin);
    }

}
