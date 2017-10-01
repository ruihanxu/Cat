package com.example.xuruihan.cats.model;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by xuruihan on 2017/9/30.
 */

public class SignupManager {

    private static SignupManager ourInstance = new SignupManager();
    private User user;

    private final String fileName = "userProfiles";

    private SignupManager() {
    }

    public static SignupManager getInstance() {
        return ourInstance;
    }

    public void doSignup(String username, String password, Boolean isAdmin, Context context) {

        if (isAdmin) {
            user = new Admin();
        } else {
            user = new Cat();
        }
    }
}
