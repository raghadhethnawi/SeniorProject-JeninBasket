package com.example.seniorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button reg_btn;
    Button log_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reg_btn = (Button) findViewById(R.id.reg_btn);
        log_btn = (Button) findViewById(R.id.log_btn);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });

        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });
    }

    public void openRegisterActivity(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void openLoginActivity(){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}