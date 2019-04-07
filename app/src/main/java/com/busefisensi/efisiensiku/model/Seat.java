package com.busefisensi.efisiensiku.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Seat implements Parcelable {

    private String nomor;
    private String tersedia;

    public Seat(){

    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Seat createFromParcel(Parcel in) {
            return new Seat(in);
        }

        public Seat[] newArray(int size) {
            return new Seat[size];
        }
    };

    public Seat(Parcel in) {
        this.nomor = in.readString();
        this.tersedia = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nomor);
        dest.writeString(this.tersedia);
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getTersedia() {
        return tersedia;
    }

    public void setTersedia(String tersedia) {
        this.tersedia = tersedia;
    }

}
