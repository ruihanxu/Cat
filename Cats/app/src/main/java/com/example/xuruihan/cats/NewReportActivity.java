package com.example.xuruihan.cats;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.xuruihan.cats.model.Report;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuruihan on 2017/10/13.
 */

public class NewReportActivity extends AppCompatActivity {
    private Button uploadButton;
    private DatabaseReference mDatabase;
    private Report report;
    private JSONObject content = new JSONObject();
    private int currentID = 40000000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newreport);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        uploadButton = (Button) findViewById(R.id.upload_button);
        uploadButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, ReportActivity.class);
            setUpReport();
            intent.putExtra("Report",report);
            //setReportJson(setUpReport());
            mDatabase.child("Entries").child(""+currentID).child("Created Date").setValue(report.getDate());
            mDatabase.child("Entries").child(""+currentID).child("Borough").setValue(report.getBorough());
            mDatabase.child("Entries").child(""+currentID).child("City").setValue("NEW YORK");
            mDatabase.child("Entries").child(""+currentID).child("Incident Address").setValue(report.getAddress());
            mDatabase.child("Entries").child(""+currentID).child("Incident Zip").setValue(report.getZip());
            mDatabase.child("Entries").child(""+currentID).child("Location Type").setValue(report.getLocationType());
            startActivity(intent);
        });

    }
    @Keep
    public Report setUpReport() {
        //currentID = 40000000;
        report = new Report(currentID,
                ((EditText)findViewById(R.id.dateInput)).getText().toString(),
                ((EditText)findViewById(R.id.locationTypeInput)).getText().toString(),
                ((EditText)findViewById(R.id.zipInput)).getText().toString(),
                "NEW YORK",//city
                ((EditText)findViewById(R.id.boroughInput)).getText().toString(),
                null, null,// longitude, latitude
                ((EditText)findViewById(R.id.addressInput)).getText().toString()
        );
        //currentID++;
        return report;
    }

    /*@Keep
    public void setReportJson(Report report) {
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
    }*/
}


