package com.busefisensi.efisiensiku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.adapter.PassengerAdapter;
import com.busefisensi.efisiensiku.database.DBHelper;
import com.busefisensi.efisiensiku.database.PassengerStorage;
import com.busefisensi.efisiensiku.model.Passenger;

import java.util.ArrayList;
import java.util.List;

public class ChoosePassengerActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Passenger>> {

    private TextView tvTitle;
    private RecyclerView rvPassenger;
    private ImageView ivAddPassenger;
    private Button btnSavePassenger;

    private List<Passenger> existingPassengers = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_choose_passenger);

        tvTitle = findViewById(R.id.tvTitle);
        rvPassenger = findViewById(R.id.rv_passenger);
        ivAddPassenger = findViewById(R.id.iv_add_passenger);
        btnSavePassenger = findViewById(R.id.btn_save_passenger);

        tvTitle.setText(getResources().getString(R.string.choose_passenger));

        Intent intent = getIntent();
        existingPassengers = intent.getParcelableArrayListExtra("passengers");

        getSupportLoaderManager().initLoader(0, null, this).forceLoad();

        btnSavePassenger.setOnClickListener(new OnSave());
    }

    @NonNull
    @Override
    public Loader<List<Passenger>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new AsyncTaskLoader<List<Passenger>>(this) {
            @Nullable
            @Override
            public List<Passenger> loadInBackground() {
                String where = "where id not in ";
                StringBuilder builder = new StringBuilder();
                builder.append("(");
                for(Passenger passenger: existingPassengers) {
                    builder.append(passenger.getId() + ", ");
                }
                builder.delete(builder.toString().length()-2, builder.toString().length());
                builder.append(")");
                where = where + builder.toString();

                PassengerStorage passengerStorage = new PassengerStorage(DBHelper.getInstance().getReadableDatabase());
                List<Passenger> passengers = passengerStorage.getAllPassengersCustomWhereClause(where);

                return passengers;
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Passenger>> loader, List<Passenger> passengers) {
        PassengerAdapter passengerAdapter = new PassengerAdapter(ChoosePassengerActivity.this, passengers, true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChoosePassengerActivity.this);
        rvPassenger.setAdapter(passengerAdapter);
        rvPassenger.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Passenger>> loader) {

    }

    private class OnSave implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            PassengerAdapter passengerAdapter = (PassengerAdapter) rvPassenger.getAdapter();
            List<Passenger> selectedPassengers = passengerAdapter.getSelectedPassenger();

            for(Passenger passenger : existingPassengers) {
                selectedPassengers.add(passenger);
            }

            Intent intent = new Intent();
            intent.putExtra("passengers", new ArrayList<>(selectedPassengers));
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private class AddPassenger implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            
        }
    }

}
