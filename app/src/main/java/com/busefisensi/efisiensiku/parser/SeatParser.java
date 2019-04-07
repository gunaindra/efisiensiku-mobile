package com.busefisensi.efisiensiku.parser;

import com.busefisensi.efisiensiku.model.Seat;
import com.javasoul.swframework.component.SWLog;
import com.javasoul.swframework.model.SWResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SeatParser {

    private SWResult result = new SWResult();

    public SeatParser(String json){
        try {

            JSONObject jsonObject = new JSONObject(json);
            String seating = jsonObject.getString("message");
            List<Seat> seats = new ArrayList<>();
            Seat seat = new Seat();
            seat.setNomor(jsonObject.getString("message"));
//            seats.add(seat);
//            JSONObject jsonObject = new JSONObject("seating");
//            JSONArray seating = jsonObject.getJSONArray("status");
//
//            List<Seat> seats = new ArrayList<>();
//            for(int i=0;i<seating.length(); i++){
//                Seat seat = new Seat();
//                JSONObject seatingobject = seating.getJSONObject(i);
//                seat.setNomor(seatingobject.getString("nomor"));
//                seat.setNomor(seatingobject.getString("tersedia"));
//
//                seats.add(seat);
//            }

            HashMap<String, Object> data = new HashMap<>();
            data.put("seats", seats);

            result.setResult(true);
            result.setData(data);
        }
        catch (JSONException e){
            result.setResult(false);
            result.addError(e.getMessage());

            SWLog.e(SeatParser.class, e.getMessage());
        }
    }

    public SWResult getResult(){
        return result;
    }
}
