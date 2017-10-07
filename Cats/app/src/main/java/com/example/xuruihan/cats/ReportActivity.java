package com.example.xuruihan.cats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.xuruihan.cats.model.Report;

/**
 * Created by Ruixuan on 10/6/17.
 */

public class ReportActivity extends AppCompatActivity {
    private Button BackToMap;
    private View locationView;
    private View cityView;
    private View addressView;
    private View zipView;
    private View dataView;
    private View keyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        BackToMap = (Button) findViewById(R.id.map_button);
        BackToMap.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        });

    }


}
