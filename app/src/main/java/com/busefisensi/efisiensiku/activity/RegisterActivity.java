package com.busefisensi.efisiensiku.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;


public class RegisterActivity extends AppCompatActivity {

    Toolbar mtoolbar;
    Button btnSimpan;
    TextView tvToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        tvToolbar = (TextView)findViewById(R.id.tvToolbar);
        tvToolbar.setText("Daftarin");
        btnSimpan = (Button)findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
