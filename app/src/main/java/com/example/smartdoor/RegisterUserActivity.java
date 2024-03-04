package com.example.smartdoor;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class RegisterUserActivity extends AppCompatActivity {

    EditText registerMail, registerPassword, registerUsername;
    ImageButton registerPageButton;
    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        registerMail = findViewById(R.id.registerMail);
        registerPassword = findViewById(R.id.registerPassword);
        registerUsername = findViewById(R.id.registerUsername);
        registerPageButton = findViewById(R.id.registerPageButton);

        registerPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterUserActivity.this, DoorControls.class);
                startActivity(intent);
            }
        });



    }
}