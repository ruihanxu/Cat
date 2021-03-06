package com.example.xuruihan.cats.model;

import android.support.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by League of Users on 2017/10/01.
 */
@SuppressWarnings("ALL")
public class User {

    @Nullable
    private static UserItem userItem;
    private static DatabaseReference mDatabase;
    /**
     * Default constructor of user to create a null user
     */
    public User () {
        this.userItem = null;
    }

    /**
     * Create a user with userItem
     * @param userItem the particular user with basic info
     */
    public User (@Nullable UserItem userItem) {
        this.userItem = userItem;
    }

    /**
     * Getter method for userItem
     * @return the userItem
     */
    public UserItem getUserItem() {
        return userItem;
    }

    /**
     * Setter for user
     * @param user the username to be set
     * @param pass the password to be set
     */
    public void setUser(String user, String pass) {
        assert userItem != null;
        userItem.setUser(user);
        userItem.setPass(pass);

    }

    /**
     * the getter for firebase
     * @param mDatabase the database to be get
     * @return the database
     */
    public static DatabaseReference getmDatabase(DatabaseReference mDatabase) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return mDatabase;
    }

    /**
     *
     * @param jsonObj the info about user read from database
     * @return the user from Json
     * @throws JSONException if the jsonObj parameter is null
     */
    public static User fromJson(JSONObject jsonObj) throws JSONException {
        return new User(userItem);

    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof  User) {
            User user = (User) object;
            //UserItem userItem = (UserItem) object;
            if (user.userItem.equals(this.userItem)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
