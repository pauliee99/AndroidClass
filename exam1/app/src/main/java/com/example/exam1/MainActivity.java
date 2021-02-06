package com.example.exam1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView result = (TextView)findViewById(R.id.result);
        Intent intent = getIntent();
        String userid = intent.getStringExtra("userid");
        //if (!userid.equals(null)){
            result.setText(userid);
        //}
    }
}