package com.busefisensi.efisiensiku.parser;

import com.busefisensi.efisiensiku.model.Schedule;
import com.javasoul.swframework.component.SWLog;
import com.javasoul.swframework.model.SWResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScheduleParser {

    private SWResult result = new SWResult();

    public ScheduleParser(String json) {
        try {

            // FIXME hardcode gara2 waktu API
            JSONObject jsonObject = new JSONObject("{\"success\":true,\"message\":\"Pengambilan jadwal berhasil.\",\"jadwal\":[{\"id\":158157,\"jadwal\":\"YKC 1400\",\"waktuBerangkat\":\"18/3/2019 14:00\",\"waktuSampai\":\"18/3/2019 19:00\",\"waktuNaik\":\"18/3/2019 14:15\",\"waktuTurun\":\"18/3/2019 19:00\",\"tarif\":70000,\"jumlahKursiKosong\":33,\"tipeBus\":\"Patas Executive 43\"},{\"id\":158156,\"jadwal\":\"YKC 1500\",\"waktuBerangkat\":\"18/3/2019 15:00\",\"waktuSampai\":\"18/3/2019 20:00\",\"waktuNaik\":\"18/3/2019 15:15\",\"waktuTurun\":\"18/3/2019 20:00\",\"tarif\":70000,\"jumlahKursiKosong\":43,\"tipeBus\":\"Patas Executive 43\"},{\"id\":158155,\"jadwal\":\"YKC 1600\",\"waktuBerangkat\":\"18/3/2019 16:00\",\"waktuSampai\":\"18/3/2019 21:00\",\"waktuNaik\":\"18/3/2019 16:15\",\"waktuTurun\":\"18/3/2019 21:00\",\"tarif\":70000,\"jumlahKursiKosong\":40,\"tipeBus\":\"Patas Executive 43\"},{\"id\":158154,\"jadwal\":\"YKC 1700\",\"waktuBerangkat\":\"18/3/2019 17:00\",\"waktuSampai\":\"18/3/2019 22:00\",\"waktuNaik\":\"18/3/2019 17:15\",\"waktuTurun\":\"18/3/2019 22:00\",\"tarif\":70000,\"jumlahKursiKosong\":43,\"tipeBus\":\"Patas Executive 43\"},{\"id\":158153,\"jadwal\":\"YKC 1800\",\"waktuBerangkat\":\"18/3/2019 18:00\",\"waktuSampai\":\"18/3/2019 23:00\",\"waktuNaik\":\"18/3/2019 18:15\",\"waktuTurun\":\"18/3/2019 23:00\",\"tarif\":70000,\"jumlahKursiKosong\":40,\"tipeBus\":\"Patas Executive 43\"},{\"id\":158152,\"jadwal\":\"YKC 1900\",\"waktuBerangkat\":\"18/3/2019 19:00\",\"waktuSampai\":\"18/3/2019 00:00\",\"waktuNaik\":\"18/3/2019 19:15\",\"waktuTurun\":\"19/3/2019 00:00\",\"tarif\":70000,\"jumlahKursiKosong\":41,\"tipeBus\":\"Patas Executive 43\"},{\"id\":158151,\"jadwal\":\"YKC 2000\",\"waktuBerangkat\":\"18/3/2019 20:00\",\"waktuSampai\":\"18/3/2019 01:00\",\"waktuNaik\":\"18/3/2019 20:15\",\"waktuTurun\":\"19/3/2019 01:00\",\"tarif\":70000,\"jumlahKursiKosong\":43,\"tipeBus\":\"Patas Executive 43\"},{\"id\":158150,\"jadwal\":\"YKC 2100\",\"waktuBerangkat\":\"18/3/2019 21:00\",\"waktuSampai\":\"18/3/2019 02:00\",\"waktuNaik\":\"18/3/2019 21:15\",\"waktuTurun\":\"19/3/2019 02:00\",\"tarif\":70000,\"jumlahKursiKosong\":41,\"tipeBus\":\"Patas Executive 43\"}]}");
            JSONArray scheduleArray = jsonObject.getJSONArray("jadwal");
            List<Schedule> schedules = new ArrayList<>();

            for(int i=0; i<scheduleArray.length(); i++) {
                JSONObject object = scheduleArray.getJSONObject(i);
                Schedule schedule = new Schedule();
                schedule.setId(object.getInt("id"));
                schedule.setSchedule(object.getString("jadwal"));
                schedule.setDepartureTime(object.getString("waktuBerangkat"));
                schedule.setArriveTime(object.getString("waktuSampai"));
                schedule.setUpTime(object.getString("waktuNaik"));
                schedule.setDownTime(object.getString("waktuTurun"));
                schedule.setPrice(object.getLong("tarif"));
                schedule.setEmptyChairs(object.getInt("jumlahKursiKosong"));
                schedule.setBusType(object.getString("tipeBus"));

                schedules.add(schedule);
            }

            HashMap<String, Object> data = new HashMap<>();
            data.put("schedules", schedules);

            result.setResult(true);
            result.setData(data);

        } catch(JSONException e) {
            result.setResult(false);
            result.addError(e.getMessage());

            SWLog.e(AgentParser.class, e.getMessage());
        }
    }

    public SWResult getResult() {
        return result;
    }
}
