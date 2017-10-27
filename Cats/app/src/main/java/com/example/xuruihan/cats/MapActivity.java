package com.example.xuruihan.cats;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewStubCompat;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.xuruihan.cats.model.Report;
import com.example.xuruihan.cats.model.ReportManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{//}, com.example.xuruihan.cats.LoadingView {


    private Button logoutButton;
    private Button reportButton;
    private FloatingActionButton newReport;
    private static final String TAG = "MapActivity";
    private DatabaseReference mDatabase;
    private View stubView;
    private ArrayList<Report> reports;

    private static EditText startDate;
    private static EditText endDate;
    private static boolean startDateSelection;
    private static boolean endDateSelection;
    private static boolean startDateSelected;

    private static String startDateString;
    private static String endDateString;

    private GoogleMap mMap;

    private static InputMethodManager imm;

    public static int currentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



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


        startDate = (EditText) findViewById(R.id.startDate);
        startDate.setOnClickListener((View v) -> {
            startDateSelection = true;
            startDateSelected = true;
            DialogFragment dialogFragment = new DatePickerFragment();
            dialogFragment.show(getSupportFragmentManager(), "datePicker");
        });

        endDate = (EditText) findViewById(R.id.endDate);
        endDate.setOnClickListener((View v) -> {
            endDateSelection = true;
            startDateSelected = false;
            DialogFragment dialogFragment = new DatePickerFragment();
            dialogFragment.show(getSupportFragmentManager(), "datePicker");
        });


         imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


/*
        // fetch big chunk of data from firebase, "reports" has the data
        // The following is a sample to get an arraylist of data.
        ReportManager reportManager = new ReportManager();
        reports = new ArrayList<>();
        reportManager.getReportsByDate(reports, "08/09/2017 12:00:00 AM", "09/09/2017 12:00:00 AM", this);
        */
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


    public void displayResult(List<Report> reportList) {
        mMap.clear();
        for (Report report : reportList) {
            if (!report.getLongitude().equals("") && !report.getLatitude().equals("")) {
                MarkerOptions markerOptions = new MarkerOptions();
                LatLng latLng = new LatLng(Double.valueOf(report.getLatitude()), Double.valueOf(report.getLongitude()));
                markerOptions.position(latLng);
                markerOptions.title(String.valueOf(report.getKey()));
                markerOptions.snippet(report.getDescription());


                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);
            }
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(40.738359, -73.998692), new LatLng(40.847450, -73.817654)));
        // Setting a click event handler for the map
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {



                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);



                // Clears the previously touched position
                // mMap.clear();
                //mFacade.addReport("newly added", "Bobs Place", new Location(latLng.latitude, latLng.longitude));

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                //markerOptions.title(mFacade.getLastReport().getName());
                //markerOptions.snippet(mFacade.getLastReport().getDescription());

                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);
            }
        });

        /*
        List<Report> reportList = mFacade.getReports();
        for (Report r : reportList) {
            LatLng loc = new LatLng(r.getLatitude(), r.getLongitude());
            mMap.addMarker(new MarkerOptions().position(loc).title(r.getName()).snippet(r.getDescription()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }
        */

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        CustomInfoWindowAdapter(){
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }


    /*
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
    */
}