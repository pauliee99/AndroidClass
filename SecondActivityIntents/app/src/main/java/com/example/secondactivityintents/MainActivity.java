package com.example.secondactivityintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.Button)
        Intent intent = getIntent();
        int a = intent.getIntExtra("integer1", -9);
        String b = intent.getStringExtra("string1");
        intent.putExtra("result", a+b);
        setResult(this.RESULT_OK,intent);
        finish();
    }
}