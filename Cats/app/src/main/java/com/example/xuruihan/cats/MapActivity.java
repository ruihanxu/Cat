package com.example.xuruihan.cats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapActivity extends AppCompatActivity {

    private Button logoutButton;
    private Button reportButton;
    private static final String TAG = "MapActivity";
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, StartUpActivity.class);
            startActivity(intent);
        });

        reportButton = (Button) findViewById(R.id.report_button);
        reportButton.setOnClickListener((View v1) -> {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("message23").setValue("hehe");

        mDatabase.child("36907530").child("City").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TextView textView1 = (TextView) findViewById(R.id.textView);
                textView1.setText((String) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // Write a message to the database
    }
}