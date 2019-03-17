package com.busefisensi.efisiensiku.constant;

public enum URL {

    AGENT("http://rbt.arutala.co.id/efisiensi-mobile/v1/agen/turun/12");

    String url;
    URL(String url) {
        this.url = url;
    }

    public String get() {
        return url;
    }
}
