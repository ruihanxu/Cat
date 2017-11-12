package com.example.xuruihan.cats;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.icu.util.Calendar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.xuruihan.cats.model.Report;
import com.example.xuruihan.cats.model.ReportManager;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Ruixuan on 10/29/17.
 */

public class GraphActivity extends AppCompatActivity implements LoadingView{

    private static final String TAG = "GraphActivity";
    private static LineGraphSeries<DataPoint> series;
    private static GraphView graph;
    private static EditText startDate;
    private static EditText endDate;
    private static boolean startDateSelection;
    private static boolean endDateSelection;
    private static boolean startDateSelected;

    private View stubView;
    
    private static String startDateString;
    private static String endDateString;
    private static InputMethodManager imm;


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

        endDate = (EditText) findViewById(R.id.graphEndDate);
        endDate.setOnClickListener((View v) -> {
            endDateSelection = true;
            startDateSelected = false;
            DialogFragment dialogFragment = new GraphActivity.DatePickerFragment();
            dialogFragment.show(getSupportFragmentManager(), "datePicker");
        });


        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    /**
     * Date Picker for users to choose date range of graph display
     */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            //Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }


        /**
         * Processing date data from the calender
         * @param view the date picker view
         * @param year the year of the graph range
         * @param month the month of the graph range
         * @param day the day of the graph range
         */
        @Override
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

                ArrayList<Report> listReport = new ArrayList<>();
                ReportManager reportManager = ReportManager.getInstance();
                Log.e(TAG, "First test: " + String.valueOf(getActivity() == null));
                reportManager.requestGraphData(startDateString.substring(0, 4), endDateString.substring(0, 4), (GraphActivity)getActivity());

            }
        }
    }


    /**
     * Graph Display based on the hashmap passed by the report manager
     * @param map the map to get data from
     */
    public void getHashMap(Map<String, String> map) {

        String[] xAxis = map.keySet().toArray(new String[map.size()]);
        Arrays.sort(xAxis);

        String yAxis[] = new String[map.values().size()];
        for (int i = 0; i < xAxis.length; i++) {
            yAxis[i] = map.get(xAxis[i]);
        }

        DataPoint datas[] = new DataPoint[yAxis.length];
        for (int i = 0; i < datas.length; i++) {
            datas[i] = new DataPoint(Integer.parseInt(xAxis[i]), Integer.parseInt(yAxis[i]));
        }


        series = new LineGraphSeries<>(datas);

        graph.addSeries(series);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);

        // set manual X bounds
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(xAxis);
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        // set manual Y bounds
        staticLabelsFormatter.setVerticalLabels(yAxis);
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);


        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);

        graph.getViewport().setScrollable(true);
        findViewById(R.id.graph).setVisibility(View.VISIBLE);
    }

    @Override
    public void setUpLoadingView() {
    }

    @Override
    public void displayResult(Object object) {
    }

}
