package com.busefisensi.efisiensiku.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.card.MaterialCardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.activity.AgentActivity;
import com.busefisensi.efisiensiku.activity.CalendarActivity;
import com.busefisensi.efisiensiku.activity.PassengerActivity;
import com.busefisensi.efisiensiku.constant.RequestCode;
import com.busefisensi.efisiensiku.model.Agent;
import com.busefisensi.efisiensiku.model.Passenger;
import com.busefisensi.efisiensiku.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class BookingFragment extends Fragment {

    private TextView tvFrom;
    private TextView tvDestination;
    private TextView tvCalendar;
    private TextView tvDay;
    private TextView tvDate;
    private TextView tvSchedule;

    private MaterialCardView cvSchedule;

    private Agent agentDeparture = new Agent();
    private Agent agentDestination = new Agent();
    private List<Passenger> passengers = new ArrayList<>();
    private String date = "";
    private Schedule schedule = new Schedule();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        tvFrom = view.findViewById(R.id.tvFrom);
        tvDestination = view.findViewById(R.id.tvDestination);
        tvCalendar = view.findViewById(R.id.tvCalendar);
        tvDay = view.findViewById(R.id.tv_day);
        tvDate = view.findViewById(R.id.tv_date);
        tvSchedule = view.findViewById(R.id.tvJam);

        cvSchedule = view.findViewById(R.id.cvJam);

        actions();

        return view;
    }

    private void actions() {
        tvFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AgentActivity.class);
                intent.putExtra("requestCode", RequestCode.CHOOSE_DEPARTURE.get());
                startActivityForResult(intent, RequestCode.CHOOSE_DEPARTURE.get());
            }
        });

        tvDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AgentActivity.class);
                intent.putExtra("requestCode", RequestCode.CHOOSE_DESTINATION.get());
                startActivityForResult(intent, RequestCode.CHOOSE_DESTINATION.get());
            }
        });

        tvCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), CalendarActivity.class), RequestCode.CHOOSE_DATE.get());
            }
        });

        cvSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PassengerActivity.class);
                intent.putParcelableArrayListExtra("passengers", new ArrayList<>(passengers));
                startActivityForResult(intent, RequestCode.CHOOSE_SCHEDULE.get());
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
            date = data.getStringExtra("fullDate");
            int date = data.getIntExtra("date", 0);
            String month = data.getStringExtra("month");
            int year = data.getIntExtra("year", 0);
            String day = data.getStringExtra("day");
            tvDay.setText(day);
            tvDate.setText(date + " " + month.substring(0,3) + " " + year);
        } else if(requestCode == RequestCode.CHOOSE_SCHEDULE.get() && data!=null) {
            passengers = data.getParcelableArrayListExtra("passengers");
            tvSchedule.setText(wrapPassengers());
        }
    }

    private String wrapPassengers() {
        StringBuilder builder = new StringBuilder();
        for(Passenger passenger: passengers) {
            builder.append(passenger.getFirstName() + " " + passenger.getLastName() + ", ");
        }

        return builder.toString().substring(0, builder.toString().length()-2);
    }

}
