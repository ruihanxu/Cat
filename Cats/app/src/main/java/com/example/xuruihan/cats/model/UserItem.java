package com.example.xuruihan.cats.model;


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
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        if (!user.equals("")) {
            userID = user;
        }
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

    @Override
    public boolean equals(Object object) {
        if (object instanceof  UserItem) {
            UserItem userItem = (UserItem) object;
            if (userItem.userID.equals(this.userID) && userItem.password.equals(this.password) && userItem.isAdmin == this.isAdmin) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
