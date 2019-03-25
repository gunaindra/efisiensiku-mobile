package com.busefisensi.efisiensiku.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.model.Passenger;

import java.util.List;

public class PassengerListAdapter extends RecyclerView.Adapter<PassengerListAdapter.MyViewHolder> {
    Context context;
    private List<Passenger> passengerList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView passenger;

        public MyViewHolder(View view){
            super(view);
            passenger = view.findViewById(R.id.tvPenumpang);
        }
    }

    public PassengerListAdapter (Context context, List<Passenger> passengerList){
        this.context = context;
        this.passengerList = passengerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_content_passenger, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        Passenger passenger = passengerList.get(position);

        holder.passenger.setText(passenger.getFirstName());
    }

    @Override
    public int getItemCount(){
        return passengerList.size();
    }
}
