package com.example.xuruihan.cats.model;

import android.content.Context;

/**
 * Created by xuruihan on 2017/9/30.
 */

public class SignupManager {

    private static SignupManager ourInstance = new SignupManager();
    private User user;

    private SignupManager() {
    }

    public static SignupManager getInstance() {
        return ourInstance;
    }

    public void doSignup(String username, String password, Boolean isAdmin, Context context) {
        
    }
}
