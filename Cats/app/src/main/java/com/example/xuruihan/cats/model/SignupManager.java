package com.example.xuruihan.cats.model;

import android.app.Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by xuruihan on 2017/9/30.
 */

@SuppressWarnings("ALL")
public class SignupManager {

    private static SignupManager ourInstance = new SignupManager();
    private User user;

    private final String fileName = "userProfiles";
    private FirebaseAuth mAuth;

    private SignupManager() {
    }

    /**
     * Get the instance of login manager.
     *
     * @return the instance of login manager
     */
    public static SignupManager getInstance() {
        return ourInstance;
    }


    /**
     * Get the instance of login manager.
     *
     * @param userUID the string of the name
     * @param password the string of the password
     * @param isAdmin the boolean to check if the user is a administrator
     * @param activity the context message of the sign up
     */
    public void doSignup(String userUID, String password, Boolean isAdmin, Activity activity) {

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(userUID).child("user type").setValue((isAdmin) ? 1 : 0);
    }
}