package com.example.dbclassexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static String DBNAME = "TEST_DB";
    public static String TABLE_NAME = "TEST";
    public static String FIELD_1 = "FNAME";
    public static String FIELD_2 = "LNAME";
    public DbHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("+FIELD_1+" TEXT, "+FIELD_2+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertRecord(ContractTest record){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_1,record.getFname());
        values.put(FIELD_2,record.getLname());
        long id = database.insert(TABLE_NAME, null, values);
        return id;
    }
}