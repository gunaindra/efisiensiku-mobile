package com.busefisensi.efisiensiku.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.activity.AgentActivity;
import com.busefisensi.efisiensiku.activity.CalendarActivity;
import com.busefisensi.efisiensiku.constant.RequestCode;
import com.busefisensi.efisiensiku.model.Agent;

public class BookingFragment extends Fragment {

    private TextView tvFrom;
    private TextView tvDestination;
    private TextView tvCalendar;
    private TextView tvDay;
    private TextView tvDate;

    private Agent agentDeparture = new Agent();
    private Agent agentDestination = new Agent();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        tvFrom = view.findViewById(R.id.tvFrom);
        tvDestination = view.findViewById(R.id.tvDestination);
        tvCalendar = view.findViewById(R.id.tvCalendar);
        tvDay = view.findViewById(R.id.tv_day);
        tvDate = view.findViewById(R.id.tv_date);

        actions();

        return view;
    }

    private void actions() {
        tvFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), AgentActivity.class), RequestCode.CHOOSE_DEPARTURE.get());
            }
        });

        tvDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), AgentActivity.class), RequestCode.CHOOSE_DESTINATION.get());
            }
        });

        tvCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), CalendarActivity.class), RequestCode.CHOOSE_DATE.get());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RequestCode.CHOOSE_DEPARTURE.get() && data != null) {
            Agent agent = data.getParcelableExtra("agent");
            if(agent!=null) {
                agentDeparture = agent;
                tvFrom.setText(agent.getAgentName());
            }
        } else if(requestCode == RequestCode.CHOOSE_DESTINATION.get() && data != null) {
            Agent agent = data.getParcelableExtra("agent");
            if(agent!=null) {
                agentDestination = agent;
                tvDestination.setText(agent.getAgentName());
            }
        } else if(requestCode == RequestCode.CHOOSE_DATE.get() && data != null) {
            int date = data.getIntExtra("date", 0);
            String month = data.getStringExtra("month");
            int year = data.getIntExtra("year", 0);
            String day = data.getStringExtra("day");
            tvDay.setText(day);
            tvDate.setText(date + " " + month.substring(0,3) + " " + year);
        }
    }
}
