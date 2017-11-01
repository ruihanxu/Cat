package com.example.xuruihan.cats;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.widget.ViewStubCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.xuruihan.cats.model.Report;
import com.example.xuruihan.cats.model.ReportManager;
import com.google.firebase.database.DatabaseReference;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Ruixuan on 10/29/17.
 */

public class GraphActivity extends AppCompatActivity implements LoadingView{

    private static final String TAG = "GraphActivity";
    private static LineGraphSeries<DataPoint> series;
    private static GraphView graph;
    private int lastX = 0;
    private static EditText startDate;
    private static EditText endDate;
    private static boolean startDateSelection;
    private static boolean endDateSelection;
    private static boolean startDateSelected;

    private View stubView;

    private Map<String, String> map;
    private static String startDateString;
    private static String endDateString;
    private static InputMethodManager imm;

    private static ReportManager reportManager;
    private static GraphActivity graphActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        // we get graph view instance
       graph = (GraphView) findViewById(R.id.graph);

        graph.getGridLabelRenderer().setHorizontalAxisTitle("Year");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Rat Sightings");


        startDate = (EditText) findViewById(R.id.gstartDate);
        startDate.setOnClickListener((View v) -> {
            startDateSelection = true;
            startDateSelected = true;
            DialogFragment dialogFragment = new GraphActivity.DatePickerFragment();
            dialogFragment.show(getSupportFragmentManager(), "datePicker");
        });

        endDate = (EditText) findViewById(R.id.gendDate);
        endDate.setOnClickListener((View v) -> {
            endDateSelection = true;
            startDateSelected = false;
            DialogFragment dialogFragment = new GraphActivity.DatePickerFragment();
            dialogFragment.show(getSupportFragmentManager(), "datePicker");
        });


        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @RequiresApi(api = Build.VERSION_CODES.N)
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
                startDate.setText(year + "/" + month + "/" + day + " 12:00:00 AM");
                startDateString = year + "/" + monthString + "/" + dayString + " 12:00:00 AM";
            } else {
                endDate.setText(year + "/" + month + "/" + day + " 12:00:00 AM" );
                endDateString = year + "/" + monthString + "/" + dayString + " 12:00:00 AM";
            }

            if (startDateSelection && endDateSelection) {
                StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                int startYear = Integer.parseInt(startDateString.substring(0, 4));
                int endYear = Integer.parseInt(endDateString.substring(0, 4));
                String[] xLabels = new String[endYear - startYear];
                for (int i = 0; i < xLabels.length; i++) {
                    xLabels[i] = String.valueOf(i + startYear);
                }




                staticLabelsFormatter.setHorizontalLabels(xLabels);

                staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
                graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                graph.getGridLabelRenderer().setNumHorizontalLabels(xLabels.length);
                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setYAxisBoundsManual(true);

                reportManager = ReportManager.getInstance();
                reportManager.requestGraphData(startDateString.substring(0,4 ), endDateString.substring(0, 4), (GraphActivity) getActivity());

                graph.addSeries(series);
                // customize a little bit viewport
                series.setDrawDataPoints(true);
                series.setDataPointsRadius(10);


            }
        }
    }
    @Override
    public void setUpLoadingView() {
        stubView = ((ViewStub) findViewById(R.id.viewstub_loading)).inflate();
    }

    @Override
    public void displayResult(Object object) {
    }

    public void getHashMap(Map<String, String> map) {
        this.map = map;
        Log.d(TAG, String.valueOf((map == null)));
    }

}
