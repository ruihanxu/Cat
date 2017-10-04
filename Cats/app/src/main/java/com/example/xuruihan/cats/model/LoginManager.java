package com.example.xuruihan.cats.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.xuruihan.cats.util.NetworkSingleton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xuruihan on 2017/09/30.
 */
public class LoginManager {
    private static final String LOGIN_URL = "";

    /**
     * Show an interface to show if login success or fail.
     */
    public interface LoginCallBack {

        /**
         * Show if login success
         *
         * @param user the user who wants to log in
         */
        void onLoginSuccess(User user);

        /**
         * Show if login fail
         *
         * @param msg the message when login failed
         */
        void onLoginFail(String msg);
    }

    private static LoginManager ourInstance = new LoginManager();
    private User user;

    /**
     * Get the instance of login manager.
     *
     * @return the instance of login manager
     */
    public static LoginManager getInstance() {
        return ourInstance;
    }

    private LoginManager() {
    }

    /**
     * CCheck if the user has logged in
     *
     * @return whether the user exists after logged in
     */
    public boolean isLogin() {
        return user != null;
    }

    /**
     * Log in to the app after type in the correct name and password
     *
     * @param username the username to log in
     * @param password the password to log in
     * @param callBack the status to check if logged in
     * @param ctx the context to tell the user about the status
     */
    public void doLogin(String username, String password, final LoginCallBack callBack, Context ctx) {
        //TODO: build url here.
        String url = LOGIN_URL;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            /**
             * show the JSON object which do correct response
             *
             * @param response the response of the JSONobject
             */
            @Override
            public void onResponse(JSONObject response) {
                try {
                    callBack.onLoginSuccess(User.fromJson(response));
                } catch (JSONException e) {
                    callBack.onLoginFail("Invalid JSON response");
                }
            }
        }, new Response.ErrorListener() {

            /**
             * show the JSON object which make error in response
             *
             * @param error the error of the JSONobject
             */
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    callBack.onLoginSuccess(User.fromJson(null));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        NetworkSingleton.getInstance(ctx).addToRequestQueue(request);
    }
}
