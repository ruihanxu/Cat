package com.example.xuruihan.cats.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ruixuan on 10/6/17.
 */

public class Report {
<<<<<<< HEAD
=======
    private int key;
    private String date;
    private String locationType;
    private String zip;

    private String city;
    private String borough;
    private String longitude;
    private String latitude;

    /**
     * Default constructor of user to create a null report
     */
    public Report() {
        this.key = 0;
        this.date = null;
        this.locationType = null;
        this.zip = null;
        this.city = null;
        this.borough = null;
        this.longitude = null;
        this.latitude = null;
    }

    /**
     *
     * @param key report ID
     * @param date the date the report was created
     * @param locationType the type of location where the rat was seen
     * @param zip the zip code of the location
     * @param city the city where the rat was seen
     * @param borough the borough of the sighting location
     * @param longitude the longitude of the sighting location
     * @param latitude the latitude of the sighting location
     */
    public Report(int key, String date, String locationType, String zip, String city, String borough, String longitude,
        String latitude) {
        this.key = key;
        this.date = date;
        this.locationType = locationType;
        this.zip = zip;
        this.city = city;
        this.borough = borough;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    private static DatabaseReference mDatabase;

    public static DatabaseReference getmDatabase(DatabaseReference mDatabase) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        return mDatabase;
    }







>>>>>>> af3df167e1aa1fd83c9a24f9aaf462706d9d5f4f

}
