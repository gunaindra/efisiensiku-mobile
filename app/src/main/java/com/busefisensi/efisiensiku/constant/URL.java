package com.busefisensi.efisiensiku.constant;

public enum URL {

    AGENT_ORIGIN("http://rbt.arutala.co.id/efisiensi-mobile-test/v1/agen/naik"),
    AGENT_DESTINATION("http://rbt.arutala.co.id/efisiensi-mobile-test/v1/agen/naik"),
    SCHEDULE("http://rbt.arutala.co.id/efisiensi-mobile/v1/jadwal/agen/4/15/2019-3-18");

    String url;
    URL(String url) {
        this.url = url;
    }

    public String get() {
        return url;
    }
}
