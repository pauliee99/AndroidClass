package com.example.mobdev2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static String DB_NAME = "LocationDB";
    public static String TABLE_NAME = "Locations";
    public static String ID = "id";
    public static String LOGTITUDE = "longitude";
    public static String LATITUDE = "latitude";
    public static String DT = "dt";
    private String SQL_QUERY = "CREATE TABLE " + TABLE_NAME +
            " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            LOGTITUDE + " REAL, " +
            LATITUDE + " REAL, " +
            DT + "VARCHAR(20) " +
            ")";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + ID + " TEXT, " + LOGTITUDE + " REAL, " + LATITUDE + " REAL, " + DT + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Returns the id of the user
     *
     * @param locationUpdates
     * @return id
     */
    public long insertLocation(LocationUpdates locationUpdates) {
        ContentValues values = new ContentValues();
        values.put(LOGTITUDE, locationUpdates.getLongitude());
        values.put(LATITUDE, locationUpdates.getLatitude());
        values.put(DT, locationUpdates.getDt());
        long id = this.getWritableDatabase().insert(TABLE_NAME, null, values);
        return id;
    }

    public Cursor selectAll(){
        return this.getReadableDatabase().query(TABLE_NAME,null,null,null,null,null,null);
    }

    public Cursor selectContactById(long id){
        return this.getReadableDatabase().query(TABLE_NAME,null,"rowid=?",new String[]{id+""},null,null,null);
    }
}
