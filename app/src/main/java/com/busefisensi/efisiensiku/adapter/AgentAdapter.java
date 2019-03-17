package com.busefisensi.efisiensiku.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.model.Agent;

import java.util.List;

public class AgentAdapter extends RecyclerView.Adapter<AgentAdapter.ViewHolder> {

    private Context context;
    private List<Agent> agents;
    private OnClickListener onClickListener;

    public AgentAdapter(Context context, List<Agent> agents, OnClickListener onClickListener) {
        this.context = context;
        this.agents = agents;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_agent,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Agent agent = agents.get(i);

        viewHolder.tvAgentName.setText(agent.getAgentName());
        viewHolder.tvAgentAddress.setText(agent.getAgentAddress());

        viewHolder.llLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(agent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return agents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAgentName;
        private TextView tvAgentAddress;
        private LinearLayout llLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAgentName = itemView.findViewById(R.id.tv_agent);
            tvAgentAddress = itemView.findViewById(R.id.tv_agent_address);
            llLayout = itemView.findViewById(R.id.ll_layout);
        }
    }

    public interface OnClickListener {
        void onClick(Agent agent);
    }

}
