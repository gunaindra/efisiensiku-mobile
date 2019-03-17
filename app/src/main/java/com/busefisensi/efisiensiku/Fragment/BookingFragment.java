package com.busefisensi.efisiensiku.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.activity.OriginActivity;
import com.busefisensi.efisiensiku.constant.RequestCode;

public class BookingFragment extends Fragment {

    private TextView tvFrom;
    private TextView tvDestination;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        tvFrom = view.findViewById(R.id.tvFrom);
        tvDestination = view.findViewById(R.id.tvDestination);

        actions();

        return view;
    }

    private void actions() {
        tvFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), OriginActivity.class), RequestCode.CHOOSE_DEPARTURE.get());
            }
        });

        tvDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), OriginActivity.class), RequestCode.CHOOSE_DESTINATION.get());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RequestCode.CHOOSE_DEPARTURE.get()) {

        } else if(requestCode == RequestCode.CHOOSE_DESTINATION.get()) {

        } else {

        }
    }
}
