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
import com.busefisensi.efisiensiku.adapter.AgentAdapter;
import com.busefisensi.efisiensiku.adapter.CityAdapter;
import com.busefisensi.efisiensiku.constant.RequestCode;
import com.busefisensi.efisiensiku.constant.URL;
import com.busefisensi.efisiensiku.model.Agent;
import com.busefisensi.efisiensiku.parser.Parser;
import com.busefisensi.efisiensiku.transport.HTTPClient;
import com.javasoul.swframework.component.SWLog;
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

public class AgentActivity extends AppCompatActivity implements AgentAdapter.OnClickListener {

    private TextView tvToolbar;
    private TextView tvOriginBody;
    private TextView tvOriginBodyAddress;
    private RecyclerView rvCity;

    private ImageView btnSearch;

    private List<Agent> agents = new ArrayList<>();
    private Agent agent = new Agent();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_origin_destination);

        tvOriginBody = findViewById(R.id.tv_origin_body);
        tvOriginBodyAddress = findViewById(R.id.tv_origin_body_address);
        tvToolbar = findViewById(R.id.tvToolbar);
        rvCity = findViewById(R.id.rv_city);
        btnSearch = findViewById(R.id.btn_search);

        tvToolbar.setText(getResources().getString(R.string.departure));
        btnSearch.setOnClickListener(new OnClickSearch());


        loadAllAgent();
    }

    private void loadAllAgent() {
        final SWProgressDialog progressDialog = new SWProgressDialog(this);
        progressDialog.showProgressIndeterminate(true);
        HTTPClient.sendHTTPGETJSON(URL.AGENT.get(), new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SWToast.showLongError(e.getMessage());
                        SWLog.e(AgentActivity.class, "loadAllAgent", e.getMessage());
                        progressDialog.dismiss();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Response res = response;
                Parser parser = new Parser(res);
                final SWResult result = parser.agentParser();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result.getResult()) {
                            SWToast.showLongSuccess("Agent has been loaded");

                            HashMap<String, Object> data = result.getData();
                            agents = (List) data.get("agents");

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AgentActivity.this);
                            CityAdapter cityAdapter = new CityAdapter(AgentActivity.this, agents, AgentActivity.this);
                            rvCity.setLayoutManager(linearLayoutManager);
                            rvCity.setAdapter(cityAdapter);
                        } else {
                            SWToast.showLongError(result.getError());
                        }
                        progressDialog.dismiss();
                    }
                });
            }
        });
    }

    private class OnClickSearch implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AgentActivity.this, SearchActivity.class);
            intent.putParcelableArrayListExtra("agents", new ArrayList<>(agents));
            startActivityForResult(intent, RequestCode.SEARCH_AGENT.get());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RequestCode.SEARCH_AGENT.get()) {
            if(data != null && data.getParcelableExtra("agent") != null) {
                Agent agent = data.getParcelableExtra("agent");
                tvOriginBody.setText(agent.getAgentName());
                tvOriginBodyAddress.setText(agent.getAgentAddress());
                this.agent = agent;
            }
        }
    }

    @Override
    public void onClick(Agent agent) {
        tvOriginBody.setText(agent.getAgentName());
        tvOriginBodyAddress.setText(agent.getAgentAddress());
        this.agent = agent;
    }

    @Override
    public void onBackPressed() {
        if(agent.getId() != null) {
            Intent intent = new Intent();
            intent.putExtra("agent", agent);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            setResult(RESULT_OK);
            finish();
        }
    }
}
