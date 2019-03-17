package com.busefisensi.efisiensiku.constant;

public enum RequestCode {

    CHOOSE_DEPARTURE(10),
    CHOOSE_DESTINATION(11),
    CHOOSE_DATE(12),
    CHOOSE_HOURS(13),
    CHOOSE_PASSENGER(14),
    SEARCH_AGENT(15);

    int requestCode;

    RequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int get() {
        return requestCode;
    }
}
