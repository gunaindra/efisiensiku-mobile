package com.busefisensi.efisiensiku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.adapter.ScheduleAdapter;
import com.busefisensi.efisiensiku.constant.URL;
import com.busefisensi.efisiensiku.model.Agent;
import com.busefisensi.efisiensiku.model.Schedule;
import com.busefisensi.efisiensiku.parser.Parser;
import com.busefisensi.efisiensiku.transport.HTTPClient;
import com.javasoul.swframework.component.SWProgressDialog;
import com.javasoul.swframework.component.SWToast;
import com.javasoul.swframework.model.SWResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ScheduleActivity extends AppCompatActivity implements ScheduleAdapter.OnChooseSchedule {

    private TextView tvTitle;
    private TextView tvDepartureDate;

    private RecyclerView rvSchedule;

    private String date = "";
    private Agent agentOrigin = new Agent();
    private Agent agentDestination = new Agent();

    private List<Schedule> schedules = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_schedule);

        tvTitle = findViewById(R.id.tvTitle);
        tvDepartureDate = findViewById(R.id.tv_departure_date);
        rvSchedule = findViewById(R.id.rv_schedule);

        tvTitle.setText(getResources().getString(R.string.choose_departure_date));

        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        agentOrigin = intent.getParcelableExtra("agentOrigin");
        agentDestination = intent.getParcelableExtra("agentDestination");

        tvDepartureDate.setText(getResources().getString(R.string.departure) + ", " + intent.getStringExtra("date"));

        loadSchedule();
    }

    private void loadSchedule() {
        StringBuilder builder = new StringBuilder();
        builder.append(URL.SCHEDULE.get());
        builder.append(agentOrigin.getId() + "/");
        builder.append(agentDestination.getId() + "/");
        builder.append(date + "/");
        builder.append(1 + "/");

        String url = builder.toString();

        // FIXME di hardcode dulu biar gampang
        url = "http://rbt.arutala.co.id/efisiensi-mobile-test/v1/jadwal/agen/5/1/19-03-2019/1";

        final SWProgressDialog progressDialog = new SWProgressDialog(this);
        progressDialog.showProgressIndeterminate(true);

        HTTPClient.sendHTTPGETJSON(url, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SWToast.showLongError(e.getMessage());
                        progressDialog.dismiss();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Parser parser = new Parser(response);
                final SWResult result = parser.scheduleParser();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result.getResult()) {
                            SWToast.showShortSuccess(getResources().getString(R.string.load_schedule_success));

                            HashMap<String, Object> data = result.getData();
                            schedules = (List) data.get("schedules");

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ScheduleActivity.this);
                            ScheduleAdapter scheduleAdapter = new ScheduleAdapter(ScheduleActivity.this, schedules, ScheduleActivity.this);
                            rvSchedule.setLayoutManager(linearLayoutManager);
                            rvSchedule.setAdapter(scheduleAdapter);
                        } else {
                            SWToast.showLongError(result.getError());
                        }

                        progressDialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public void chooseSchedule(Schedule schedule) {
        Intent intent = new Intent();
        intent.putExtra("schedule", schedule);
        setResult(RESULT_OK, intent);
        finish();
    }
}
