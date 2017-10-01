package com.example.xuruihan.cats.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by League of Users on 2017/10/01.
 */
public class User {

    UserItem userItem;

    public UserItem getUserItem() {
        return userItem;
    }
    public void setUser(String user, String pass) {
        userItem.setUser(user);
        userItem.setPass(pass);
    }
    public static User fromJson(JSONObject jsonObj) throws JSONException {
        //TODO: init necessary fields.
        return new User();
    }
}
