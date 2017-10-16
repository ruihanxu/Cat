package com.example.xuruihan.cats.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.xuruihan.cats.R;
import com.example.xuruihan.cats.SignUpFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;


/**
 * Created by xuruihan on 2017/9/30.
 */

public class SignupManager {

    private static SignupManager ourInstance = new SignupManager();
    private User user;

    private final String fileName = "userProfiles";
    private FirebaseAuth mAuth;

    private SignupManager() {
    }

    /**
     * Get the instance of login manager.
     *
     * @return the instance of login manager
     */
    public static SignupManager getInstance() {
        return ourInstance;
    }


    /**
     * Get the instance of login manager.
     *
     * @param username the string of the name
     * @param password the string of the password
     * @param isAdmin the boolean to check if the user is a administrator
     * @param context the context message of the sign up
     */
    private boolean isSuccessful = false;

    public boolean doSignup(String userUID, String password, Boolean isAdmin, Activity activity) {

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(userUID).child("user type").setValue("haha");
//        mAuth = FirebaseAuth.getInstance();
//        mAuth.createUserWithEmailAndPassword(username, password)
//                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        isSuccessful = task.isSuccessful();
//                        if (task.isSuccessful()) {
//                        } else {
//                            FirebaseAuthException e = (FirebaseAuthException )task.getException();
//                            Log.d(TAG, "hahaha");
//                            Log.e(TAG, "Registration failure", e);
//                        }
//                    }
//                });
//
//        if (isAdmin) {
//            user = new Admin();
//        } else {
//            user = new Cat();
//        }


        return isSuccessful;
    }
}