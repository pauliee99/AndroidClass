package com.example.firstactivityintents;

import androidx.annotation.Nullable;
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
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName(  "com.example.secondactivityintents", "com.example.secondactivityintents.MainActivity");
                intent.putExtra("integer1", 5);
                intent.putExtra("string1", "Hello World");
                startActivityForResult(intent, 7);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7){
            if (resultCode == RESULT_OK){
                Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
            }
        }
    }
}