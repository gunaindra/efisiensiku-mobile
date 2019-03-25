package com.busefisensi.efisiensiku.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper mInstance = null;
    private static Context applicationContext;

    private static final String databaseName = "efisiensiku.db";
    private static final int databaseVersion = 1;

    public DBHelper(Context context) {
        super(context, databaseName, null, databaseVersion);

        applicationContext = context;
    }

    public static DBHelper getInstance() {
        if(mInstance == null) {
            mInstance = new DBHelper(applicationContext);
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PassengerStorage.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS passengers");
        onCreate(db);
    }
}
