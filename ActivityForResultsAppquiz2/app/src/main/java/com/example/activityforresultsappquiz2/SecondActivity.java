package com.example.activityforresultsappquiz2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText editText1 = findViewById(R.id.editTextNumber1);
                EditText editText2 = findViewById(R.id.editTextNumber2);
                intent.putExtra("num1", Integer.parseInt(editText1.getText().toString()));
                intent.putExtra("num2", Integer.parseInt(editText2.getText().toString()));
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}