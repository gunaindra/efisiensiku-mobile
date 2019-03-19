package com.busefisensi.efisiensiku.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.model.Passenger;

import java.util.ArrayList;
import java.util.List;

public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.ViewHolder> {

    private Context context;
    private List<Passenger> passengers;
    private Boolean selection;

    public PassengerAdapter(Context context, List<Passenger> passengers, Boolean selection) {
        this.context = context;
        this.passengers = passengers;
        this.selection = selection;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_passenger_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Passenger passenger = passengers.get(i);

        viewHolder.tvPassengerName.setText(passenger.getFirstName() + " " + passenger.getLastName());

        if(selection) {
            viewHolder.ivSelection.setBackground(context.getResources().getDrawable(R.drawable.ic_selection_false));
            viewHolder.rlPassengerItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(viewHolder.ivSelection.getBackground().getConstantState().equals(context.getResources().getDrawable(R.drawable.ic_selection_true).getConstantState())) {
                        viewHolder.ivSelection.setBackground(context.getResources().getDrawable(R.drawable.ic_selection_false));
                        passengers.get(i).setSelected(false);
                    } else {
                        viewHolder.ivSelection.setBackground(context.getResources().getDrawable(R.drawable.ic_selection_true));
                        passengers.get(i).setSelected(true);
                    }
                }
            });
        } else {
            viewHolder.ivSelection.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return passengers.size();
    }

    public List<Passenger> getSelectedPassenger() {
        List<Passenger> selectedPassenger = new ArrayList<>();
        for(Passenger passenger:passengers) {
            if(passenger.getSelected()) {
                selectedPassenger.add(passenger);
            }
        }

        return selectedPassenger;
    }

    public Integer getPassengerId(int position) {
        return passengers.get(position).getId();
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPassengerName;
        private ImageView ivSelection;
        private RelativeLayout rlPassengerItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rlPassengerItem = itemView.findViewById(R.id.rl_passenger_item);
            tvPassengerName = itemView.findViewById(R.id.tv_passenger_name);
            ivSelection = itemView.findViewById(R.id.iv_selection);
        }
    }

}
