package com.example.activityforresultsappquiz2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0){
            if (resultCode == RESULT_OK){
                int a = data.getIntExtra("num1", 0);
                int b = data.getIntExtra("num2", 0);
                TextView textView = findViewById(R.id.textview);
                textView.setText((a+b)+"");
            }
            if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "An error occured!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}