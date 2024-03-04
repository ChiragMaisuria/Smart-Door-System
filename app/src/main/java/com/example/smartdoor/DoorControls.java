package com.example.smartdoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoorControls extends AppCompatActivity {

    ImageButton lockBtn, unlockBtn;
    ImageView imgChanger;
    Button buzzOn, buzzOff;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_controls);

        imgChanger = findViewById(R.id.imageViewDoorStatus);
        lockBtn = findViewById(R.id.lockBtn);
        unlockBtn = findViewById(R.id.unlockBtn);
        buzzOn = findViewById(R.id.buzzOnBtn);
        buzzOff = findViewById(R.id.buzzOffBtn);
        DatabaseReference dbReference = firebaseDatabase.getInstance().getReference().child("MainDoor");

        buzzOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbReference.child("Buzzer").setValue("On");
            }
        });

        buzzOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbReference.child("Buzzer").setValue("Off");
            }
        });

        dbReference.child("Status").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue().toString().equals("close")){
                    imgChanger.setImageResource(R.drawable.doorc);
                }else if(snapshot.getValue().toString().equals("open")){
                    imgChanger.setImageResource(R.drawable.dooro);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbReference.child("Mode").setValue("lock");
//                dbReference.child("Mode").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        dbReference.child("Mode").setValue("lock");
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
            }
        });
        unlockBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                dbReference.child("Mode").setValue("unlock");
//                dbReference.child("Mode").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        dbReference.child("Mode").setValue("unlock");
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
            }
        });

//        dbReference.child("Mode").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                dbReference.child("Mode").setValue("lock");
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        imgChanger.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                imgChanger.setImageResource(R.drawable.doorc);
//            }
//        });
    }
}