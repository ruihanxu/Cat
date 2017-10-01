package com.example.xuruihan.cats.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xuruihan on 2016/11/19.
 */
public class User {

    UserItem userItem;

    public User () {
        this.userItem = null;
    }

    public User (UserItem userItem) {
        this.userItem = userItem;
    }

    public UserItem getUserItem() {
        return userItem;
    }
    public void setUser(String user, String pass) {
        userItem.setUser(user);
        userItem.setPass(pass);
    }

    public static User fromJson(JSONObject jsonObj) throws JSONException {
        //TODO: init necessary fields.
        //return new User();
        //read from file
        return new User();
    }
}
