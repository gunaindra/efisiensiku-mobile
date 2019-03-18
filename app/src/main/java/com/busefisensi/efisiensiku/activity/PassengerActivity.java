package com.busefisensi.efisiensiku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.adapter.PassengerAdapter;
import com.busefisensi.efisiensiku.constant.RequestCode;
import com.busefisensi.efisiensiku.model.Agent;
import com.busefisensi.efisiensiku.model.Passenger;

import java.util.ArrayList;
import java.util.List;

public class PassengerActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvInformationDeparture;
    private TextView tvInformationDateTime;
    private TextView tvInformationDestination;
    private TextView tvEmptyPassenger;

    private RecyclerView rvPassenger;

    private ImageView ivAddPassenger;

    private List<Passenger> passengers = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_passanger);

        tvTitle = findViewById(R.id.tvTitle);
        tvInformationDeparture = findViewById(R.id.tv_information_departure);
        tvInformationDateTime = findViewById(R.id.tv_information_date_time);
        tvInformationDestination = findViewById(R.id.tv_information_destination);
        tvEmptyPassenger = findViewById(R.id.tv_empty_passenger);

        rvPassenger = findViewById(R.id.rv_passenger);

        ivAddPassenger = findViewById(R.id.iv_add_passenger);

        tvTitle.setText(getResources().getString(R.string.passenger_informations));

        Intent intent = getIntent();
        if(intent!=null && intent.getParcelableArrayListExtra("passengers") != null) {
            passengers = intent.getParcelableArrayListExtra("passengers");
            loadPassenger();

            Agent departure = intent.getParcelableExtra("departure");
            Agent destination = intent.getParcelableExtra("destination");
            String date = intent.getParcelableExtra("date");
            String time = intent.getParcelableExtra("time");

            tvInformationDeparture.setText(departure.getAgentCityName());
            tvInformationDestination.setText(destination.getAgentCityName());
            tvInformationDateTime.setText(date + ", Pukul " + time);
        }

        ivAddPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PassengerActivity.this, ChoosePassengerActivity.class);
                intent.putParcelableArrayListExtra("passengers", new ArrayList<>(passengers));
                startActivityForResult(intent, RequestCode.ADD_OR_CHOOSE_PASSENGER.get());
            }
        });

    }

    private void loadPassenger() {
        PassengerAdapter passengerAdapter = new PassengerAdapter(this, passengers, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvPassenger.setLayoutManager(linearLayoutManager);
        rvPassenger.setAdapter(passengerAdapter);

        if(passengers.size()==0) {
            rvPassenger.setVisibility(View.GONE);
            tvEmptyPassenger.setVisibility(View.VISIBLE);
        } else {
            rvPassenger.setVisibility(View.VISIBLE);
            tvEmptyPassenger.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RequestCode.ADD_OR_CHOOSE_PASSENGER.get() && data != null) {
            List<Passenger> newPassenger = data.getParcelableArrayListExtra("passenger");
            for(Passenger passenger: newPassenger) {
                passengers.add(passenger);
            }
        }
    }
}
