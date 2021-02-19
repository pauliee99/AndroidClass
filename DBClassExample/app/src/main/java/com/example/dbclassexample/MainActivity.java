package com.example.dbclassexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper helper = new DbHelper(MainActivity.this);
        SQLiteDatabase database = helper.getWritableDatabase();
        ContractTest test = new ContractTest(  "John", "Doe");

        long id = helper.insertRecord(test);
        if(id <= 0){
            Toast.makeText(this, "oh no anyway", Toast.LENGTH_SHORT).show();
        }


        database = helper.getReadableDatabase();
        Cursor cursor = database.query(DbHelper.TABLE_NAME, new String[]{DbHelper.FIELD_1},DbHelper.FIELD_2+"=?", new String[]{"Doe"}, null, null, null );

        if(cursor.moveToFirst()){
            do{
                Toast.makeText(this, cursor.getString(0), Toast.LENGTH_SHORT).show();

            }while(cursor.moveToNext()); }




    }


}