package com.example.xuruihan.cats.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.attr.data;

/**
 * Created by Ruixuan on 10/6/17.
 */

public class Report implements Parcelable{

    private int key;
    private String date;
    private String locationType;
    private String zip;
    private String city;
    private String borough;
    private String longitude;
    private String latitude;
    private String address;

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
        this.address = null;
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
        String latitude, String address) {
        this.key = key;
        this.date = date;
        this.locationType = locationType;
        this.zip = zip;
        this.city = city;
        this.borough = borough;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Parcelling part
    public Report(Parcel in){
        this.key = in.readInt();
        String[] data = new String[8];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.date = data[0];
        this.locationType = data[1];
        this.zip = data[2];
        this.city = data[3];
        this.borough = data[4];
        this.longitude = data[5];
        this.latitude = data[6];
        this.address = data[7];
    }


    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.key);
        dest.writeStringArray(new String[] {this.date,
                this.locationType,
                this.zip,
                this.city,
                this.borough,
                this.longitude,
                this.latitude,
                this.address,
        });
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        public Report[] newArray(int size) {
            return new Report[size];
        }
    };
}
