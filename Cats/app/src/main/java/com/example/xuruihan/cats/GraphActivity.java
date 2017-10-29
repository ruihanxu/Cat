package com.example.xuruihan.cats;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.xuruihan.cats.model.Report;
import com.example.xuruihan.cats.model.ReportManager;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

/**
 * Created by Ruixuan on 10/29/17.
 */

public class GraphActivity extends AppCompatActivity{

    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;
    private static EditText startDate;
    private static EditText endDate;
    private static boolean startDateSelection;
    private static boolean endDateSelection;
    private static boolean startDateSelected;

    private static String startDateString;
    private static String endDateString;
    private static InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        // we get graph view instance
        GraphView graph = (GraphView) findViewById(R.id.graph);
        // data
        series = new LineGraphSeries<DataPoint>();
        graph.addSeries(series);
        // customize a little bit viewport
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(10);
        viewport.setScrollable(true);

        startDate = (EditText) findViewById(R.id.startDate);
        startDate.setOnClickListener((View v) -> {
            startDateSelection = true;
            startDateSelected = true;
            DialogFragment dialogFragment = new MapActivity.DatePickerFragment();
            dialogFragment.show(getSupportFragmentManager(), "datePicker");
        });

        endDate = (EditText) findViewById(R.id.endDate);
        endDate.setOnClickListener((View v) -> {
            endDateSelection = true;
            startDateSelected = false;
            DialogFragment dialogFragment = new MapActivity.DatePickerFragment();
            dialogFragment.show(getSupportFragmentManager(), "datePicker");
        });


        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            Log.d("DEBUG DATE",view.toString());
            // Do something with the date chosen by the user
            month = month + 1;
            String monthString = month < 10 ? "0" + month : "" + month;
            String dayString = day < 10 ? "0" + day : "" + day;
            if (startDateSelected) {
                startDate.setText(month + "/" + day + "/" + year + " 12:00:00 AM");
                startDateString = monthString + "/" + dayString + "/" + year + " 12:00:00 AM";
            } else {
                endDate.setText(month + "/" + day + "/" + year + " 12:00:00 AM" );
                endDateString = monthString + "/" + dayString + "/" + year + " 12:00:00 AM";
            }

            if (startDateSelection && endDateSelection) {
                ArrayList<Report> listReport = new ArrayList<>();
                ReportManager reportManager = new ReportManager();
                reportManager.getReportsByDate(listReport, startDateString, endDateString, (MapActivity)getContext());
            }
        }
    }





}
