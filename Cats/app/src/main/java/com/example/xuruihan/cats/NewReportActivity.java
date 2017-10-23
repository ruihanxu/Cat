package com.example.xuruihan.cats;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.xuruihan.cats.model.Report;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.xuruihan.cats.MapActivity.currentID;


/**
 * Created by xuruihan on 2017/10/13.
 */

public class NewReportActivity extends AppCompatActivity {
    private Button uploadButton;
    private Button cancelButton;
    private DatabaseReference mDatabase;
    private Report report;

    private static final String TAG = "NewReportActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newreport);

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

        uploadButton = (Button) findViewById(R.id.upload_button);
        uploadButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, MapActivity.class);

            //give HistoryActivity a boolean that a new item is on the list
            //Intent intent1 = new Intent(this, HistoryActivity.class);
            //intent.putExtra("isAdded", intent1);

            //get user input to a new Report object

            setUpReport();
            //update currentID for the next key
            currentID++;

            intent.putExtra("Report",report);
            //update IDcounter for the next key
            mDatabase.child("IDcounter").child("counter").setValue(currentID);


            //upload data
            mDatabase.child("Entries").child(""+currentID).child("Created Date").setValue(report.getDate());
            mDatabase.child("Entries").child(""+currentID).child("Borough").setValue(report.getBorough());
            mDatabase.child("Entries").child(""+currentID).child("City").setValue("NEW YORK");
            mDatabase.child("Entries").child(""+currentID).child("Incident Address").setValue(report.getAddress());
            mDatabase.child("Entries").child(""+currentID).child("Incident Zip").setValue(report.getZip());
            mDatabase.child("Entries").child(""+currentID).child("Location Type").setValue(report.getLocationType());

            HistoryActivity.keys.add(currentID);

            startActivity(intent);
        });

        cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        });

    }

    /**
     * Get user's input to new a Report object for up loading
     */
    @Keep
    public Report setUpReport() {
        //currentID = 40000000;
        report = new Report(currentID + 1,
                ((EditText)findViewById(R.id.dateInput)).getText().toString(),
                ((EditText)findViewById(R.id.locationTypeInput)).getText().toString(),
                ((EditText)findViewById(R.id.zipInput)).getText().toString(),
                "NEW YORK",//city
                ((EditText)findViewById(R.id.boroughInput)).getText().toString(),
                null, null,// longitude, latitude
                ((EditText)findViewById(R.id.addressInput)).getText().toString()
        );
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


