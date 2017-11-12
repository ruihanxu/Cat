package com.example.xuruihan.cats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


public class StartUpActivity extends AppCompatActivity implements StartUpFragment.OnStartUpFragmentInteractionListener,
                LoginFragment.OnLoginFragmentInteractionListener, SignUpFragment.OnSignupFragmentInteractionListener{

    private static FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);


        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        StartUpFragment startUpFragment = new StartUpFragment();
        fragmentTransaction.add(R.id.fragmentContainer, startUpFragment).commit();
    }


    @Override
    public void goToLogin() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.replace(R.id.fragmentContainer, loginFragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    @Override
    public void goToSignup() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        SignUpFragment signupFragment = new SignUpFragment();
        fragmentTransaction.replace(R.id.fragmentContainer, signupFragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    @Override
    public void cancelSignup() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        StartUpFragment startUpFragment = new StartUpFragment();
        fragmentTransaction.replace(R.id.fragmentContainer, startUpFragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    @Override
    public void cancelLogin() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        StartUpFragment startUpFragment = new StartUpFragment();
        fragmentTransaction.replace(R.id.fragmentContainer, startUpFragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    @Override
    public void signupToMainPage() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToMainPage() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}
