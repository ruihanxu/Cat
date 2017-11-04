package com.example.xuruihan.cats.model;

import android.util.Log;

import com.example.xuruihan.cats.GraphActivity;
import com.example.xuruihan.cats.LoadingView;
import com.example.xuruihan.cats.MapActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ruixuan on 10/7/17.
 */

public class ReportManager {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();;
    private Query query;
    private static ReportManager ourInstance = new ReportManager();
    private final String TAG = "ReportManager";

    private ReportManager() {}

    public static ReportManager getInstance() {
        return ourInstance;
    }

    /**
     * Method to get the latest updated report
     * @param returnArrayList the updated arraylist of reports
     * @param amount limit to the last updated report
     * @param callback the loading view
     * @throws IllegalArgumentException if returnArrayList is null or amount is smaller than zero
     */
    public void getLatestReports(ArrayList<Report> returnArrayList, int amount, LoadingView callback) {
        if (returnArrayList == null || amount < 0) {
            throw new IllegalArgumentException("enter valid args");
        }
        callback.setUpLoadingView();
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
                if (callback instanceof MapActivity) {
                    callback.displayResult(returnArrayList);
                } else {
                    assignGraphData(returnArrayList, callback);
                }
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
    public void getReportsByDate(ArrayList<Report> returnArrayList, String startDate, String endDate, LoadingView callback) {
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
                    Log.d(TAG, report.getDate());
                }
                if (callback instanceof MapActivity) {
                    callback.displayResult(returnArrayList);
                } else {
                    Log.d(TAG, "haha");
                    assignGraphData(returnArrayList, callback);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
    }

    public void requestGraphData(String startYear, String endYear, LoadingView callback) {
        ArrayList<Report> arrayList = new ArrayList<>();
        getReportsByDate(arrayList, startYear, endYear, callback);
        callback.setUpLoadingView();
    }

    private void assignGraphData(ArrayList<Report> returnArrayList, LoadingView callback) {
        Map<String, String> returnMap = new HashMap<>();
        for (Report report: returnArrayList) {
            String key = report.getDate().substring(0, 4);
            if (returnMap.get(key) == null) {returnMap.put(key, "1");}
            else {returnMap.put(key, String.valueOf(Integer.parseInt(returnMap.get(key)) + 1));}
        }
        ((GraphActivity)callback).getHashMap(returnMap);
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
