package com.busefisensi.efisiensiku.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.busefisensi.efisiensiku.model.Passenger;

import java.util.ArrayList;
import java.util.List;

public class PassengerStorage {

    private SQLiteDatabase db;

    private final String TABLE_NAME = "passenger";
    private final String ID = "id";
    private final String FIRST_NAME = "first_name";
    private final String LAST_NAME = "last_name";
    private final String EMAIL = "email";
    private final String TELEPHONE = "telephone";

    public PassengerStorage(SQLiteDatabase db) {
        this.db = db;
    }

//    public List<Passenger> getAllPassengers() {
//        Cursor cursor = db.rawQuery("select * from passenger", null);
//        cursor.moveToNext();
//
//        List<Passenger> passengers = new ArrayList<>();
//        while(cursor.isAfterLast() == false) {
//            Passenger passenger = new Passenger();
//            passenger.setFirstName(cursor.getColumnName(cursor.getColumnIndex(FIRST_NAME)));
//            passenger.setLastName(cursor.getColumnName(cursor.getColumnIndex(LAST_NAME)));
//            passenger.setEmail(cursor.getColumnName(cursor.getColumnIndex(EMAIL)));
//            passenger.setTelephon(cursor.getColumnName(cursor.getColumnIndex(TELEPHONE)));
//
//            passengers.add(passenger);
//        }
//
//        return passengers;
//    }
//
    public List<Passenger> getAllPassengers(){
        List<Passenger> passengers = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from passenger", null);
        if(cursor.moveToFirst()){
            do{
                Passenger passenger = new Passenger();
                passenger.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                passenger.setFirstName(cursor.getString(cursor.getColumnIndex(FIRST_NAME)));
                passenger.setLastName(cursor.getString(cursor.getColumnIndex(LAST_NAME)));
                passenger.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                passenger.setTelephon(cursor.getString(cursor.getColumnIndex(TELEPHONE)));

                passengers.add(passenger);
            }while (cursor.moveToNext());
        }
//        db.close();
        return passengers;
    }

    public List<Passenger> getAllPassengersCustomWhereClause(String where) {
        Cursor cursor = db.rawQuery("select * from passenger " + where, null);
        cursor.moveToNext();

        List<Passenger> passengers = new ArrayList<>();
        while(cursor.isAfterLast() == false) {
            Passenger passenger = new Passenger();
            passenger.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            passenger.setFirstName(cursor.getString(cursor.getColumnIndex(FIRST_NAME)));
            passenger.setLastName(cursor.getString(cursor.getColumnIndex(LAST_NAME)));
            passenger.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            passenger.setTelephon(cursor.getString(cursor.getColumnIndex(TELEPHONE)));

            passengers.add(passenger);
            cursor.moveToNext();
        }

        return passengers;
    }

    public long insert(Passenger passenger) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME, passenger.getFirstName());
        contentValues.put(LAST_NAME, passenger.getLastName());
        contentValues.put(EMAIL, passenger.getEmail());
        contentValues.put(TELEPHONE, passenger.getTelephon());

        return db.insert(TABLE_NAME, null, contentValues );
    }

    public long delete(Integer id) {
        return db.delete(TABLE_NAME, "id = ?", new String[]{id.toString()});
    }

    public static String createTable() {
        return "CREATE TABLE passenger (id integer primary key autoincrement, first_name varchar(100), last_name varchar(100), email varchar(100), telephone varchar(100));";
    }

}
