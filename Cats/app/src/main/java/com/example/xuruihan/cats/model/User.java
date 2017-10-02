package com.example.xuruihan.cats.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by League of Users on 2017/10/01.
 */
public class User {

    UserItem userItem;

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
    public User (UserItem userItem) {
        this.userItem = userItem;
    }

    /**
     * Getter method for userItem
     * @return the userItem
     */
    public UserItem getUserItem() {
        return userItem;
    }
    public void setUser(String user, String pass) {
        userItem.setUser(user);
        userItem.setPass(pass);
    }

    /**
     *
     * @param jsonObj the info about user read from database
     * @return the user from Json
     * @throws JSONException if the jsonObj parameter is null
     */
    public static User fromJson(JSONObject jsonObj) throws JSONException {
        //TODO: init necessary fields.
        //return new User();
        //read from file
        return new User();
    }
}
