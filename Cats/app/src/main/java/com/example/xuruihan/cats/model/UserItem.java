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

    public UserItem (String userID, String password, Boolean isAdmin) {
        this.userID = userID;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public void setUser(String user) {
        userID = user;
    }
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
