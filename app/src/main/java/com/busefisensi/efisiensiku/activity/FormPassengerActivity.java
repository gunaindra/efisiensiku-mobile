package com.busefisensi.efisiensiku.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.database.DBHelper;
import com.busefisensi.efisiensiku.database.PassengerStorage;
import com.busefisensi.efisiensiku.model.Passenger;
import com.javasoul.swframework.component.SWToast;

public class FormPassengerActivity extends AppCompatActivity {

    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtEmail;
    private EditText txtNoTelephone;

    private Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_form_passenger);

        txtFirstName = findViewById(R.id.txt_first_name);
        txtLastName = findViewById(R.id.txt_last_name);
        txtEmail = findViewById(R.id.txt_email);
        txtNoTelephone = findViewById(R.id.txt_no_telp);

        btnSave = findViewById(R.id.btn_save_passenger);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = txtFirstName.getText().toString();
                String lastName = txtLastName.getText().toString();
                String email = txtEmail.getText().toString();
                String noTelephone = txtNoTelephone.getText().toString();

                if(validate()) {
                    Passenger passenger = new Passenger();
                    passenger.setFirstName(firstName);
                    passenger.setLastName(lastName);
                    passenger.setEmail(email);
                    passenger.setTelephon(noTelephone);

                    PassengerStorage passengerStorage = new PassengerStorage(DBHelper.getInstance().getWritableDatabase());
                    long inserted = passengerStorage.insert(passenger);

                    if(inserted>0) {
                        SWToast.showShortSuccess(getResources().getString(R.string.save_success));
                        finish();
                    } else {
                        SWToast.showShortFailed(getResources().getString(R.string.save_failed));
                    }
                }
            }
        });
    }

    private boolean validate() {
        boolean valid = true;

        if(txtFirstName.getText().toString().equals("")) {
            txtFirstName.setError(getResources().getString(R.string.first_name) + " must be filled");
            valid = false;
        }

        if(txtLastName.getText().toString().equals("")) {
            txtLastName.setError(getResources().getString(R.string.last_name) + " must be filled");
            valid = false;
        }

        if(txtEmail.getText().toString().equals("")) {
            txtEmail.setError(getResources().getString(R.string.email) + " must be filled");
            valid = false;
        }

        if(txtNoTelephone.getText().toString().equals("")) {
            txtNoTelephone.setError(getResources().getString(R.string.no_telephone) + " must be filled");
            valid = false;
        }

        return valid;
    }

}
