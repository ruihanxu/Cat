package com.example.xuruihan.cats;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewStubCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.xuruihan.cats.model.Report;
import com.example.xuruihan.cats.model.ReportManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements com.example.xuruihan.cats.LoadingView {


    private Button logoutButton;
    private Button reportButton;
    private FloatingActionButton newReport;
    private static final String TAG = "MapActivity";
    private DatabaseReference mDatabase;
    private View stubView;
    private ArrayList<Report> reports;

    public static int currentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("IDcounter").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentID = (int)(long) dataSnapshot.child("counter").getValue();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

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

        // fetch big chunk of data from firebase, "reports" has the data
        // The following is a sample to get an arraylist of data.
        /**
        ReportManager reportManager = new ReportManager();
        reports = new ArrayList<>();
        reportManager.getReportsByDate(reports, "08/09/2017 12:00:00 AM", "09/09/2017 12:00:00 AM", this);
         */
    }

    @Override
    public void setUpLoadingView() {
        stubView = ((ViewStubCompat) findViewById(R.id.viewstub_loading)).inflate();
    }

    @Override
    public void setDownLoadingView() {
        stubView.setVisibility(View.GONE);
        findViewById(R.id.main_panel).setVisibility(View.VISIBLE);
        if (reports.size() != 0) {Log.d(TAG, reports.get(0).getAddress());}
        else {Log.d(TAG, "hehe");}
    }
}