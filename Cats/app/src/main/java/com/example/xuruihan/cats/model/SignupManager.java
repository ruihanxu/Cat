package com.example.xuruihan.cats.model;

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

    public void doSignup(String username, String password) {
        
    }
}
