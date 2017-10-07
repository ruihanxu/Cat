package com.example.xuruihan.cats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MapActivity extends AppCompatActivity {

    private Button logoutButton;
    private Button reportButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, StartUpActivity.class);
            startActivity(intent);
        });

        reportButton = (Button) findViewById(R.id.report_button);
        reportButton.setOnClickListener((View v1) -> {
            Intent intent = new Intent(this, ReportActivity.class);
            startActivity(intent);
        });

    }
}
