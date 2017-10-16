package com.example.xuruihan.cats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.xuruihan.cats.model.Report;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuruihan on 2017/10/13.
 */

public class NewReportActivity extends AppCompatActivity {
    Button uploadButton;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newreport);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        uploadButton = (Button) findViewById(R.id.upload_button);
        uploadButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, ReportActivity.class);
            upload(mDatabase);
            startActivity(intent);
        });

    }
    public Report setUpReport() {
        Report newRep = new Report(40000000,
                ((EditText)findViewById(R.id.dateInput)).getText().toString(),
                ((EditText)findViewById(R.id.locationTypeInput)).getText().toString(),
                ((EditText)findViewById(R.id.zipInput)).getText().toString(),
                "NEW YORK",//city
                ((EditText)findViewById(R.id.boroughInput)).getText().toString(),
                "", "",// longitude, latitude
                ((EditText)findViewById(R.id.addressInput)).getText().toString()
        );
        return newRep;
    }
    public Map getReportJson(Report report) {
        Map<String, JSONObject> reportJson = new HashMap();
        try {
            JSONObject content = new JSONObject();
            try {
                content.put("Borough", report.getBorough());
                content.put("City", report.getCity());
                content.put("Created Date", report.getDate());
                content.put("Incident Address", report.getAddress());
                content.put("Incident Zip", report.getZip());
                content.put("Latitude", report.getLatitude());
                content.put("Location Type", report.getLocationType());
                content.put("Longitude", report.getLatitude());
            } catch (JSONException e) {
                // TODO: catch block
                e.printStackTrace();
            }
            String key = "" + report.getKey();
            reportJson.put(key, content);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reportJson;
    }
    public void upload(DatabaseReference mDatabase) {
        mDatabase.child("Entries").setValue(getReportJson(setUpReport()));
    }
}

