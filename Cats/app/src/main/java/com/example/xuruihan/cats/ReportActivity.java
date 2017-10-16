package com.example.xuruihan.cats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xuruihan.cats.model.Report;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.data;
import static android.R.attr.key;

/**
 * Created by Ruixuan on 10/6/17.
 */

public class ReportActivity extends AppCompatActivity {
    private Button BackToMap;
    private static final String TAG = "ReportActivity";

    private DatabaseReference mDatabase;
    private Report report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        report = getIntent().getParcelableExtra("Report");

        BackToMap = (Button) findViewById(R.id.map_button);
        BackToMap.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        });

        if (report != null) {
            TextView key = (TextView) findViewById(R.id.textViewKey);
            TextView date = (TextView) findViewById(R.id.textViewDate);
            TextView zip = (TextView) findViewById(R.id.textViewZip);
            TextView address = (TextView) findViewById(R.id.textViewAddress);
            TextView city = (TextView) findViewById(R.id.textViewCity);
            TextView locationType = (TextView) findViewById(R.id.textViewLocationType);
            key.setText(Integer.toString(report.getKey()));
            date.setText(report.getDate());
            zip.setText(report.getZip());
            address.setText(report.getAddress());
            city.setText(report.getCity());
            locationType.setText(report.getLocationType());
        }
    }
}
