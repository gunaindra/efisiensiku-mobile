package com.busefisensi.efisiensiku.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.model.Schedule;
import com.busefisensi.efisiensiku.util.StringUtil;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private Context context;
    private List<Schedule> schedules;
    private OnChooseSchedule onChooseSchedule;

    public ScheduleAdapter(Context context, List<Schedule> schedules, OnChooseSchedule onChooseSchedule) {
        this.context = context;
        this.schedules = schedules;
        this.onChooseSchedule = onChooseSchedule;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_schedule_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Schedule schedule = schedules.get(i);

        viewHolder.tvScheduleTitle.setText(schedule.getSchedule());
        viewHolder.tvScheduleHour.setText("Pukul " + schedule.getUpTime());
        viewHolder.tvScheduleChair.setText("Tersedia " + schedule.getEmptyChairs() + " kursi");
        viewHolder.tvSchedulePrice.setText(StringUtil.getPriceInRupiahFormat(schedule.getPrice()));
//                (StringUtil.getPriceInRupiahFormat(schedule.getPrice()));

        viewHolder.rlScheduleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChooseSchedule.chooseSchedule(schedule);
            }
        });

    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvScheduleHour;
        private TextView tvScheduleChair;
        private TextView tvSchedulePrice;
        private TextView tvScheduleTitle;
        private RelativeLayout rlScheduleItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvScheduleTitle = itemView.findViewById(R.id.tv_schedule_title);
            tvScheduleHour = itemView.findViewById(R.id.tv_schedule_hour);
            tvScheduleChair = itemView.findViewById(R.id.tv_schedule_chairs);
            tvSchedulePrice = itemView.findViewById(R.id.tv_schedule_price);
            rlScheduleItem = itemView.findViewById(R.id.schedule_item);
        }
    }

    public interface OnChooseSchedule {
        void chooseSchedule(Schedule schedule);
    }

}
