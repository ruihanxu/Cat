package com.example.xuruihan.cats.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


/**
 * Created by xuruihan on 2017/9/30.
 */

public class SignupManager {

    private static SignupManager ourInstance = new SignupManager();
    private User user;

    private final String fileName = "userProfiles";

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
     * @param username the string of the name
     * @param password the string of the password
     * @param isAdmin the boolean to check if the user is a administrator
     * @param context the context message of the sign up
     */
    public void doSignup(String username, String password, Boolean isAdmin, Context context) {

        if (isAdmin) {
            user = new Admin();
        } else {
            user = new Cat();
        }

        SharedPreferences preferences = context.getSharedPreferences("username", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(username, password);

        editor.commit();

        String myvar = preferences.getString(username, "empty");
        Log.d("fetched password", myvar);

    }
}
