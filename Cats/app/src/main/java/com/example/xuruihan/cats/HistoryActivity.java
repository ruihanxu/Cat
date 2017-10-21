package com.example.xuruihan.cats;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.xuruihan.cats.model.Report;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.xuruihan.cats.MapActivity.currentID;

/**
 * Created by xuruihan on 2017/10/7.
 */


public class HistoryActivity extends AppCompatActivity {
    /*private Button button_detail1;
    private Button button_detail2;
    private Button button_detail3;
    private Button button_detail4;
    private Button button_detail5;
    private Button button_detail6;*/

    private DatabaseReference mDatabase;

    private static final String TAG = "HistoryActivity";

    private Report report1;
    private Report report2;
    private Report report3;
    private Report report4;
    private Report report5;
    private Report report6;
    private Report[] reportArray = {report1, report2, report3, report4, report5, report6};
    private int currentID;

    ArrayAdapter<String> adapter;

    ArrayList<String> listItems = new ArrayList<String>();

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        if (mListView == null) {
            mListView = (ListView) findViewById(R.id.listView1);
            mListView.setClickable(true);
        }

        if (listItems.isEmpty()) {
            listItems.add("null report");
        }

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked perform some action...
                Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
                // Then you start a new Activity via Intent
                Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                intent.putExtra("position", position);
                // Or / And
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });


        /**
         * get reports from database
         *//*
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        int[] keys = {currentID, 11464394, 15641584, 31614374, 35927676, 28765083};

        for (int i = 0; i < 6; i++) {
            // Read from the database
            int finalI = i;
            mDatabase.child("Entries").child(String.valueOf(keys[i])).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    reportArray[finalI] = new Report(keys[finalI],
                            (String) dataSnapshot.child("Created Date").getValue(),
                            (String) dataSnapshot.child("Location Type").getValue(),
                            (String) dataSnapshot.child("Incident Zip").getValue(),
                            (String) dataSnapshot.child("City").getValue(),
                            (String) dataSnapshot.child("Borough").getValue(),
                            (String) dataSnapshot.child("Longitude").getValue(),
                            (String) dataSnapshot.child("Latitude").getValue(),
                            (String) dataSnapshot.child("Incident Address").getValue()
                    );
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        }*/
    }

    protected ListView getListView() {
        if (mListView == null) {
            mListView = (ListView) findViewById(R.id.listView1);
        }
        return mListView;
    }

    protected void setListAdapter(ListAdapter adapter) {
        getListView().setAdapter(adapter);
    }

    protected ListAdapter getListAdapter() {
        ListAdapter adapter = getListView().getAdapter();
        if (adapter instanceof HeaderViewListAdapter) {
            return ((HeaderViewListAdapter) adapter).getWrappedAdapter();
        } else {
            return adapter;
        }
    }

        /**
         * Detailed report show up after click report buttons
         */

        /*button_detail1 = (Button) findViewById(R.id.report1);
        button_detail1.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, ReportActivity.class);
            intent.putExtra("Report", reportArray[0]);
            startActivity(intent);
        });

        button_detail2 = (Button) findViewById(R.id.report2);
        button_detail2.setOnClickListener((View v1) -> {
            Intent intent = new Intent(this, ReportActivity.class);
            int[] key = {11464394};
            intent.putExtra("Report", reportArray[1]);
            startActivity(intent);
        });

        button_detail3 = (Button) findViewById(R.id.report3);
        button_detail3.setOnClickListener((View v1) -> {
            Intent intent = new Intent(this, ReportActivity.class);
            int[] key = {15641584};
            intent.putExtra("Report", reportArray[2]);
            startActivity(intent);
        });

        button_detail4 = (Button) findViewById(R.id.report4);
        button_detail4.setOnClickListener((View v1) -> {
            Intent intent = new Intent(this, ReportActivity.class);
            int[] key = {31614374};
            intent.putExtra("Report", reportArray[3]);
            startActivity(intent);
        });

        button_detail5 = (Button) findViewById(R.id.report5);
        button_detail5.setOnClickListener((View v1) -> {
            Intent intent = new Intent(this, ReportActivity.class);
            int[] key = {35927676};
            intent.putExtra("Report", reportArray[4]);
            startActivity(intent);
        });

        button_detail6 = (Button) findViewById(R.id.report6);
        button_detail6.setOnClickListener((View v1) -> {
            Intent intent = new Intent(this, ReportActivity.class);
            int[] key = {28765083};
            intent.putExtra("Report", reportArray[5]);
            startActivity(intent);
        });
    }*/
}
