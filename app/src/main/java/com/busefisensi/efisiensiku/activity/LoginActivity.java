package com.busefisensi.efisiensiku.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;

public class LoginActivity extends AppCompatActivity {

    Button btnRegister;
    TextView tvLewati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnRegister = findViewById(R.id.btnDaftar);
        tvLewati = findViewById(R.id.tvSkip);

        btnRegister.setOnClickListener(new onRegister());
        tvLewati.setOnClickListener(new onSkip());
    }

    private class onRegister implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private class onSkip implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
    }

}
