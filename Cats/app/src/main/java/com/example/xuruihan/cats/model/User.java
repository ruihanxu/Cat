package com.example.xuruihan.cats.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xuruihan on 2016/11/19.
 */
public class User {
    public static final String USERNAME_FIELD = "username";
    public static final String PASSWORD_FIELD = "password";

    String username;
    String password;

    public static User fromJson(JSONObject jsonObj) throws JSONException {
        //TODO: init necessary fields.
        return new User();
    }
}
