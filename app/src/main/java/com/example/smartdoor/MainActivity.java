package com.example.smartdoor;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    EditText loginMail, loginPassword;
    ImageButton loginBtn, registerBtn;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginMail = findViewById(R.id.loginMail);
        loginPassword = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginButton);
        registerBtn = findViewById(R.id.registerButton);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DoorControls.class);
                startActivity(intent);

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }
        });

    }
}