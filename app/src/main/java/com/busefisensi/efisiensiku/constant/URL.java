package com.busefisensi.efisiensiku.constant;

public enum URL {

    AGENT("http://rbt.arutala.co.id/efisiensi-mobile-test/v1/agen/naik");

    String url;
    URL(String url) {
        this.url = url;
    }

    public String get() {
        return url;
    }
}
