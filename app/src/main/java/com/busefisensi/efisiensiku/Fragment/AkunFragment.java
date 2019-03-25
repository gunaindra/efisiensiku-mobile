package com.busefisensi.efisiensiku.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.activity.ChoosePassengerActivity;
import com.busefisensi.efisiensiku.activity.FormPassengerActivity;
import com.busefisensi.efisiensiku.activity.PassengerActivity;
import com.busefisensi.efisiensiku.adapter.PassengerListAdapter;
import com.busefisensi.efisiensiku.constant.RequestCode;
import com.busefisensi.efisiensiku.database.DBHelper;
import com.busefisensi.efisiensiku.database.PassengerStorage;
import com.busefisensi.efisiensiku.database.SpUser;
import com.busefisensi.efisiensiku.model.Passenger;

import java.util.ArrayList;
import java.util.List;

import static com.busefisensi.efisiensiku.model.Session.passengers;

public class AkunFragment extends Fragment {
    private List<Passenger> passengerList = new ArrayList<>();
    TextView tvToolbar;
    TextView tvNama;
    TextView tvEmail;
    TextView tvHandphone;
    ImageView iv_add_passenger;
    View view;
    SpUser spUser;
    RecyclerView recyclerView;

    public static AkunFragment newInstance() {
        AkunFragment fragment = new AkunFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){
        view = inflater.inflate(R.layout.fragment_akun, container, false);

        spUser = new SpUser(view.getContext());
        retrieveData();

        PassengerStorage passengerStorage = new PassengerStorage(DBHelper.getInstance().getReadableDatabase());
        recyclerView = view.findViewById(R.id.rv_passenger);

        passengerList.addAll(passengerStorage.getAllPassengers());

        iv_add_passenger = view.findViewById(R.id.iv_add_passenger);
        iv_add_passenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FormPassengerActivity.class);
//                intent.putParcelableArrayListExtra("passengers", new ArrayList<>(passengers));
//                startActivityForResult(intent, RequestCode.ADD_OR_CHOOSE_PASSENGER.get());
                startActivity(intent);
            }
        });
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        PassengerListAdapter passengerListAdapter = new PassengerListAdapter(getContext(), passengerList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(passengerListAdapter);
        passengerListAdapter.notifyDataSetChanged();
    }



    private void retrieveData(){
        tvNama = view.findViewById(R.id.tvNama);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvHandphone = view.findViewById(R.id.tvPhone);
        try {
            String namaDepan = spUser.getNamaDepan();
            String namaBelakang = spUser.getNamaBelakang();
            String email = spUser.getEmail();
            String handphone = spUser.getHandphone();
            Boolean isLogin = spUser.getIsLogin();

            tvNama.setText(namaDepan + " " + namaBelakang);
            tvEmail.setText(email);
            tvHandphone.setText(handphone);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
