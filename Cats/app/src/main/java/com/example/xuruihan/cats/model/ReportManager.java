package com.example.xuruihan.cats.model;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.example.xuruihan.cats.LoadingView;
import com.example.xuruihan.cats.MapActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Ruixuan on 10/7/17.
 */

public class ReportManager {

    private DatabaseReference mDatabase;
    private Query query;

    /**
     * Method to get the latest updated report
     * @param returnArrayList the updated arraylist of reports
     * @param amount limit to the last updated report
     * @param callback the loading view
     */
    public void getLatestReports(ArrayList<Report> returnArrayList, int amount, LoadingView callback) {
        callback.setUpLoadingView();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        query = mDatabase.child("Entries").orderByChild("Created Date").limitToLast(amount);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Report report = new Report(Integer.parseInt(postSnapshot.getKey()),
                            (String) postSnapshot.child("Created Date").getValue(),
                            (String) postSnapshot.child("Location Type").getValue(),
                            (String) postSnapshot.child("Incident Zip").getValue(),
                            (String) postSnapshot.child("City").getValue(),
                            (String) postSnapshot.child("Borough").getValue(),
                            (String) postSnapshot.child("Longitude").getValue(),
                            (String) postSnapshot.child("Latitude").getValue(),
                            (String) postSnapshot.child("Incident Address").getValue());
                    returnArrayList.add(report);
                }
                callback.setDownLoadingView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
    }

    /**
     * Methods to get reports by date
     * @param returnArrayList the result list of reports to be returned
     * @param startDate the start date
     * @param endDate the end date
     * @param callback the loading view
     */
    public void getReportsByDate(ArrayList<Report> returnArrayList, String startDate, String endDate, MapActivity callback) {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        query = mDatabase.child("Entries").orderByChild("Created Date").startAt(startDate).endAt(endDate);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Report report = new Report(Integer.parseInt(postSnapshot.getKey()),
                            (String) postSnapshot.child("Created Date").getValue(),
                            (String) postSnapshot.child("Location Type").getValue(),
                            (String) postSnapshot.child("Incident Zip").getValue(),
                            (String) postSnapshot.child("City").getValue(),
                            (String) postSnapshot.child("Borough").getValue(),
                            (String) postSnapshot.child("Longitude").getValue(),
                            (String) postSnapshot.child("Latitude").getValue(),
                            (String) postSnapshot.child("Incident Address").getValue());
                    returnArrayList.add(report);
                    Log.d(TAG, report.getAddress());
                }
                callback.displayResult(returnArrayList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
    }



//
//    /**
//     * Methods to get reports by date
//     * @param returnArrayList the result list of reports to be returned
//     * @param startDate the start date
//     * @param endDate the end date
//     * @param callback the graph view
//     */
//    public void getGraphReportsByDate(ArrayList<Report> returnArrayList, String startDate, String endDate, GraphActivity graph) {
//
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        query = mDatabase.child("Entries").orderByChild("Created Date").startAt(startDate).endAt(endDate);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    Report report = new Report(Integer.parseInt(postSnapshot.getKey()),
//                            (String) postSnapshot.child("Created Date").getValue(),
//                            (String) postSnapshot.child("Location Type").getValue(),
//                            (String) postSnapshot.child("Incident Zip").getValue(),
//                            (String) postSnapshot.child("City").getValue(),
//                            (String) postSnapshot.child("Borough").getValue(),
//                            (String) postSnapshot.child("Longitude").getValue(),
//                            (String) postSnapshot.child("Latitude").getValue(),
//                            (String) postSnapshot.child("Incident Address").getValue());
//                    returnArrayList.add(report);
//                    Log.d(TAG, report.getAddress());
//                }
//                callback.displayResult(returnArrayList);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        });
//    }

}
