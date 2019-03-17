package com.busefisensi.efisiensiku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.adapter.AgentAdapter;
import com.busefisensi.efisiensiku.model.Agent;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements AgentAdapter.OnClickListener {

    private EditText txtSearch;
    private ImageView btnSearch;
    private RecyclerView rvAgents;

    private List<Agent> agents = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_search);

        txtSearch = findViewById(R.id.txt_search);
        btnSearch = findViewById(R.id.btn_search);
        rvAgents = findViewById(R.id.rv_agents);

        Intent intent = getIntent();
        agents = intent.getParcelableArrayListExtra("agents");

        loadData();

        btnSearch.setOnClickListener(new OnSearch());
    }

    private void loadData() {
        AgentAdapter agentAdapter = new AgentAdapter(SearchActivity.this, agents, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
        rvAgents.setLayoutManager(linearLayoutManager);
        rvAgents.setAdapter(agentAdapter);
    }

    private class OnSearch implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            List<Agent> filteredAgent = new ArrayList<>();
            String searchKeyword = txtSearch.getText().toString().toLowerCase();
            for(Agent agent: agents) {
                if(agent.getAgentName().toLowerCase().contains(searchKeyword)) {
                    filteredAgent.add(agent);
                }
            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
            AgentAdapter agentAdapter = new AgentAdapter(SearchActivity.this, filteredAgent, SearchActivity.this);
            rvAgents.setLayoutManager(linearLayoutManager);
            rvAgents.setAdapter(agentAdapter);
        }
    }

    @Override
    public void onClick(Agent agent) {
        Intent intent = new Intent();
        intent.putExtra("agent", agent);
        setResult(RESULT_OK, intent);
        finish();
    }
}
