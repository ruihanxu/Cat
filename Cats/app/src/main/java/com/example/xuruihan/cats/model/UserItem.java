package com.example.xuruihan.cats.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xuruihan on 2017/9/30.
 */

public class UserItem {
    String userID;
    String password;
    Boolean isAdmin;

    /**
     * the constructor of UserItem
     * @param userID the username of the userItem
     * @param password the password of userItem
     * @param isAdmin the account status of the userItem
     */
    public UserItem (String userID, String password, Boolean isAdmin) {
        this.userID = userID;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    /**
     * Setter for username
     * @param user the user to be set
     */
    public void setUser(String user) {
        userID = user;
    }

    /**
     * Setter for password
     * @param pass the password to be set
     */
    public void setPass(String pass) {
        password = pass;
    }
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    //public static UserItem get

    /*public static User fromJson(JSONObject jsonObj) throws JSONException {
        //TODO: init necessary fields.
        //return new User();
        //read from file

    }
    */
}
