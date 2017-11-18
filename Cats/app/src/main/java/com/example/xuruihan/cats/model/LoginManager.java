package com.example.xuruihan.cats.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import static android.R.attr.value;
import static android.content.ContentValues.TAG;

/**
 * Created by xuruihan on 2017/09/30.
 */
@SuppressWarnings("ALL")
public class LoginManager {
    private static final String LOGIN_URL = "";
    DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    boolean isAdmin;

    /**
     * Show an interface to show if login success or fail.
     */
    public interface LoginCallBack {

        /**
         * Show if login success
         *
         * @param user the user who wants to log in
         */
        void onLoginSuccess(User user);

        /**
         * Show if login fail
         *
         * @param msg the message when login failed
         */
        void onLoginFail(String msg);
    }

    private static LoginManager ourInstance = new LoginManager();
    private User user;

    /**
     * Get the instance of login manager.
     *
     * @return the instance of login manager
     */
    public static LoginManager getInstance() {
        return ourInstance;
    }

    private LoginManager() {
    }

    /**
     * CCheck if the user has logged in
     *
     * @return whether the user exists after logged in
     */
    public boolean isLogin() {
        return user != null;
    }

    /**
     * Log in to the app after type in the correct name and password
     *  @param userUID
     * @param password the password to log in
     * @param callBack the status to check if logged in
     */
    public void doLogin(String userUID, String password, final LoginCallBack callBack) {
        mDatabase.child("Users").child(userUID).child("user type").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                isAdmin = ((long) dataSnapshot.getValue() > 0);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        User currentUser = new User(new UserItem(userUID, password, isAdmin));
        callBack.onLoginSuccess(currentUser);
    }
}

