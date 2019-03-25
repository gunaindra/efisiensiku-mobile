package com.busefisensi.efisiensiku.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.database.DBHelper;
import com.busefisensi.efisiensiku.database.PassengerStorage;
import com.busefisensi.efisiensiku.database.SpUser;
import com.busefisensi.efisiensiku.model.Passenger;
import com.javasoul.swframework.component.SWToast;


public class RegisterActivity extends AppCompatActivity {

    Toolbar mtoolbar;
    Button btnSimpan;
    TextView tvToolbar;
    EditText etRegNamaDepan;
    EditText etRegNamaBelakang;
    EditText etRegEmail;
    EditText etRegHandphone;
    Passenger passenger;
    final PassengerStorage passengerStorage = new PassengerStorage(DBHelper.getInstance().getWritableDatabase());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
//        DBHelper.getInstance().onCreate();
        tvToolbar = (TextView)findViewById(R.id.tvToolbar);
        tvToolbar.setText("Daftarin");
//        passengerStorage.createTable();

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(passengerStorage);
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void saveData(final PassengerStorage passengerStorage){
        SpUser spUser = new SpUser(RegisterActivity.this);
        passenger = new Passenger();

        etRegNamaDepan = findViewById(R.id.etRegNamaDepan);
        etRegNamaBelakang = findViewById(R.id.etRegNamaBelakang);
        etRegEmail = findViewById(R.id.etRegEmail);
        etRegHandphone = findViewById(R.id.etRegNoHandphone);



        try {
            String namaDepan = etRegNamaDepan.getText().toString().trim();
            String namaBelakang = etRegNamaBelakang.getText().toString().trim();
            String email = etRegEmail.getText().toString().trim();
            String handphone = etRegHandphone.getText().toString().trim();

            spUser.setNamaDepan(namaDepan);
            spUser.setNamaBelakang(namaBelakang);
            spUser.setEmail(email);
            spUser.setHandphone(handphone);
            spUser.setIsLogin(true);
            passenger.setFirstName(namaDepan);
            passenger.setLastName(namaBelakang);
            passenger.setEmail(email);
            passenger.setTelephon(handphone);

            long inserted = passengerStorage.insert(passenger);

            if(inserted>0) {
                SWToast.showShortSuccess(getResources().getString(R.string.save_success));
                finish();
            } else {
                SWToast.showShortFailed(getResources().getString(R.string.save_failed));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
