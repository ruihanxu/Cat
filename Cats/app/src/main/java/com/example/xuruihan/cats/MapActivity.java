package com.example.xuruihan.cats;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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
    private FloatingActionButton newReport;
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

        newReport = (FloatingActionButton) findViewById(R.id.new_button);
        newReport.setOnClickListener((View v2) -> {
            Intent intent = new Intent(this, NewReportActivity.class);
            startActivity(intent);
        });
    }
}