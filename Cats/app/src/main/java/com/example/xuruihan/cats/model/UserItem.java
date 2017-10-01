package com.example.xuruihan.cats.model;

/**
 * Created by xuruihan on 2017/9/30.
 */

public class UserItem {
    String userID;
    String password;

    public UserItem (String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public void setUser(String user) {
        userID = user;
    }
    public void setPass(String pass) {
        password = pass;
    }

    //public static UserItem get
}
