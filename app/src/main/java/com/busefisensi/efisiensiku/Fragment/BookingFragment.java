package com.busefisensi.efisiensiku.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.busefisensi.efisiensiku.activity.ScheduleActivity;
import com.busefisensi.efisiensiku.constant.RequestCode;
import com.busefisensi.efisiensiku.model.Agent;
import com.busefisensi.efisiensiku.model.Passenger;
import com.busefisensi.efisiensiku.model.Schedule;
import com.busefisensi.efisiensiku.model.Session;
import com.busefisensi.efisiensiku.util.DateUtil;
import com.javasoul.swframework.component.SWDialog;
import com.javasoul.swframework.component.SWSharedPreference;

import java.util.ArrayList;
import java.util.List;

public class BookingFragment extends Fragment {

    private TextView tvFrom;
    private TextView tvDestination;
    private TextView tvCalendar;
    private TextView tvDay;
    private TextView tvDate;
    private TextView tvSchedule;
    private TextView tvPassenger;

    private MaterialCardView cvSchedule;

    private Agent agentDeparture;
    private Agent agentDestination;
    private List<Passenger> passengers = new ArrayList<>();
    private String day;
    private String date;
    private String dateBeautify;
    private String hour;
    private Schedule schedule;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        tvFrom = view.findViewById(R.id.tvFrom);
        tvDestination = view.findViewById(R.id.tvDestination);
        tvCalendar = view.findViewById(R.id.tvCalendar);
        tvDay = view.findViewById(R.id.tv_day);
        tvDate = view.findViewById(R.id.tv_date);
        tvSchedule = view.findViewById(R.id.tvJam);
        tvPassenger = view.findViewById(R.id.tv_passenger);

        cvSchedule = view.findViewById(R.id.cvJam);

        actions();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Session.agentDeparture = agentDeparture;
        Session.agentDestination = agentDestination;
        Session.passengers = passengers;
        Session.date = date;
        Session.dateBeautify = dateBeautify;
        Session.hour = hour;
        Session.schedule = schedule;
        Session.day = day;
    }

    @Override
    public void onStop() {
        super.onStop();

        onSaveInstanceState(new Bundle());
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (Session.agentDeparture != null && Session.agentDeparture.getAgentName() != null) {
            tvFrom.setText(Session.agentDeparture.getAgentName());
        }

        if (Session.agentDestination != null && Session.agentDestination.getAgentName() != null) {
            tvDestination.setText(Session.agentDestination.getAgentName());
        }

        if (Session.day != null && !Session.day.equals("")) {
            tvDay.setText(Session.day);
        }

        if (Session.dateBeautify != null && !Session.dateBeautify.equals("")) {
            tvDate.setText(Session.dateBeautify);
        }

        if (Session.hour != null && !Session.hour.equals("")) {
            tvSchedule.setText(Session.hour);
        }

        if (Session.passengers != null && Session.passengers.size() > 0) {
            tvPassenger.setText(wrapPassengers(Session.passengers));
        }
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
                if (validate(date, agentDeparture, agentDestination)) {
                    Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                    intent.putExtra("date", date);
                    intent.putExtra("agentOrigin", agentDeparture);
                    intent.putExtra("agentDestination", agentDestination);

                    startActivityForResult(intent, RequestCode.CHOOSE_SCHEDULE.get());
                } else {
                    SWDialog.warning(getActivity(), "Validation", "Make sure date, departure agent, departure destination has been filled");
                }
            }
        });

        tvPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate(agentDeparture, agentDestination, date, schedule)) {
                    Intent intent = new Intent(getActivity(), PassengerActivity.class);
                    intent.putParcelableArrayListExtra("passengers", new ArrayList<>(passengers));
                    intent.putExtra("departure", agentDeparture);
                    intent.putExtra("destination", agentDestination);
                    intent.putExtra("date", dateBeautify);
                    intent.putExtra("time", hour);

                    startActivityForResult(intent, RequestCode.CHOOSE_PASSENGER.get());
                } else {
                    SWDialog.warning(getActivity(), "Validation", "Make sure date, departure agent, departure destination, and schedule has been filled");
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode.CHOOSE_DEPARTURE.get() && data != null) {
            Agent agent = data.getParcelableExtra("agent");
            if (agent != null) {
                agentDeparture = agent;
                tvFrom.setText(agent.getAgentName());
            }
        } else if (requestCode == RequestCode.CHOOSE_DESTINATION.get() && data != null) {
            Agent agent = data.getParcelableExtra("agent");
            if (agent != null) {
                agentDestination = agent;
                tvDestination.setText(agent.getAgentName());
            }
        } else if (requestCode == RequestCode.CHOOSE_DATE.get() && data != null) {
            date = data.getStringExtra("fullDate");
            int date = data.getIntExtra("date", 0);
            String month = data.getStringExtra("month");
            int year = data.getIntExtra("year", 0);
            day = data.getStringExtra("day");
            tvDay.setText(day);
            dateBeautify = date + " " + month.substring(0, 3) + " " + year;
            tvDate.setText(dateBeautify);
        } else if (requestCode == RequestCode.CHOOSE_SCHEDULE.get() && data != null) {
            schedule = data.getParcelableExtra("schedule");
            hour = DateUtil.getHourFromDateString(schedule.getUpTime());
            tvSchedule.setText(hour);
        } else if (requestCode == RequestCode.CHOOSE_PASSENGER.get() && data != null) {
            passengers = data.getParcelableArrayListExtra("passengers");
            tvPassenger.setText(wrapPassengers(passengers));
        }
    }

    private String wrapPassengers(List<Passenger> passengers) {
        StringBuilder builder = new StringBuilder();
        for (Passenger passenger : passengers) {
            builder.append(passenger.getFirstName() + " " + passenger.getLastName() + ", ");
        }

        return builder.toString().substring(0, builder.toString().length() - 2);
    }

    private Boolean validate(Object... objects) {
        Boolean valid = true;
        for (Object object : objects) {
            if (object == null) {
                valid = false;
            }
        }

        return valid;
    }

}
