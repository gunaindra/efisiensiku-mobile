package com.busefisensi.efisiensiku.constant;

public enum URL {

    AGENT_ORIGIN("http://rbt.arutala.co.id/efisiensi-mobile-test/v1/agen/naik"),
    AGENT_DESTINATION("http://rbt.arutala.co.id/efisiensi-mobile-test/v1/agen/turun"),
    SCHEDULE("http://rbt.arutala.co.id/efisiensi-mobile-test/v1/jadwal/agen/");

    String url;
    URL(String url) {
        this.url = url;
    }

    public String get() {
        return url;
    }
}
