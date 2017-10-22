package com.example.xuruihan.cats.model;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 10/21/17.
 */

public class History {
    private DatabaseReference mDatabase;

    private List<Report> reportArray = new ArrayList<>();

    private int currentID;

    ArrayList<String> listItems = new ArrayList<String>();

    private Report newReport;

    public History(List<Report> reports) {
        reports = reportArray;
    }

    public List<Report> getReports() {
        return reportArray;
    }

    public void setReports(List<Report> reports) {
        reportArray = reports;
    }

    public void addReport(Report report) {
        reportArray.add(newReport);
    }



}
