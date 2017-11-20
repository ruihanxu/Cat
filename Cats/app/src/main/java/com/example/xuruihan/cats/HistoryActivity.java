package com.example.xuruihan.cats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
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
import java.util.List;

/**
 * Created by xuruihan on 2017/10/7.
 */



@SuppressWarnings("ALL")
public class HistoryActivity extends AppCompatActivity implements LoadingView{

    private DatabaseReference mDatabase;

    private static final String TAG = "HistoryActivity";

    private List<Report> reportArray = new ArrayList<>();



//    static ArrayList<Integer> keys = new ArrayList<>();


    static Integer[] arrayKey = {11464394, 15641584, 31614374, 35927676, 28765083, 36908696, 36910927, 36910928,
            36910929, 36911066, 36911067, 36911108, 36911109, 36911110, 36911128, 36912108};
    static ArrayList<Integer> keys = new ArrayList<>();

    private int currentID;

    ArrayAdapter<String> adapter;

    ArrayList<String> listItems = new ArrayList<>();

    private ListView mListView;
    private Report newReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        for(Integer x: arrayKey){
            if (!keys.contains(x))
                keys.add(x);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        newReport = getIntent().getParcelableExtra("Report");

        if (mListView == null) {
            mListView = (ListView) findViewById(R.id.listView1);
            mListView.setClickable(true);
        }

        if (newReport != null) {
            reportArray.add(0, newReport);
            listItems.add(""+ newReport.getKey());
        }

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            // When clicked perform some action...
            Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
            // Then you start a new Activity via Intent
            Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
            intent.putExtra("position", position);
            // Or / And
            intent.putExtra("id", id);
            intent.putExtra("Report", reportArray.get(position));
            startActivity(intent);
        });


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


        for (int i = 0; i < keys.size(); i++) {
            // Read from the database
            int finalI = i;
            mDatabase.child("Entries").child(String.valueOf(keys.get(i))).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    Report r = new Report(keys.get(finalI),
                            (String) dataSnapshot.child("Created Date").getValue(),
                            (String) dataSnapshot.child("Location Type").getValue(),
                            (String) dataSnapshot.child("Incident Zip").getValue(),
                            (String) dataSnapshot.child("City").getValue(),
                            (String) dataSnapshot.child("Borough").getValue(),
                            (String) dataSnapshot.child("Longitude").getValue(),
                            (String) dataSnapshot.child("Latitude").getValue(),
                            (String) dataSnapshot.child("Incident Address").getValue()
                    );

                    reportArray.add(r);


                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        }

        for (int i:keys) {
            listItems.add(""+ i);
        }
    }

    /**
     * Getter for the listview
     * @return the listview
     */
    protected ListView getListView() {
        if (mListView == null) {
            mListView = (ListView) findViewById(R.id.listView1);
        }
        return mListView;
    }

    /**
     * Setter for list adapter
     * @param adapter the adapter to be set
     */
    protected void setListAdapter(ListAdapter adapter) {
        getListView().setAdapter(adapter);
    }

    /**
     * Getter for list adapter
     * @return the list adapter
     */
    protected ListAdapter getListAdapter() {
        ListAdapter adapter = getListView().getAdapter();
        if (adapter instanceof HeaderViewListAdapter) {
            return ((HeaderViewListAdapter) adapter).getWrappedAdapter();
        } else {
            return adapter;
        }
    }

    @Override
    public void setUpLoadingView() {

    }

    @Override
    public void displayResult(Object object) {

    }


}
