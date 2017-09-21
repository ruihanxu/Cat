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
 * Created by xuruihan on 2016/11/19.
 */
public class LoginManager {
    private static final String LOGIN_URL = "";

    public interface LoginCallBack {
        void onLoginSuccess(User user);
        void onLoginFail(String msg);
    }

    private static LoginManager ourInstance = new LoginManager();
    private User user;

    public static LoginManager getInstance() {
        return ourInstance;
    }

    private LoginManager() {
    }

    public boolean isLogin() {
        return user != null;
    }

    public void doLogin(String username, String password, final LoginCallBack callBack, Context ctx) {
        //TODO: build url here.
        String url = LOGIN_URL;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    callBack.onLoginSuccess(User.fromJson(response));
                } catch (JSONException e) {
                    callBack.onLoginFail("Invalid JSON response");
                }
            }
        }, new Response.ErrorListener() {
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
