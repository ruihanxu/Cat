package com.example.xuruihan.cats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by xuruihan on 2017/10/7.
 */

public class HistoryActivity extends AppCompatActivity {
    private Button button_detail1;
    private Button button_detail2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        button_detail1 = (Button) findViewById(R.id.report1);
        button_detail1.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, ReportActivity.class);
            startActivity(intent);
        });

        button_detail2 = (Button) findViewById(R.id.report2);
        button_detail2.setOnClickListener((View v1) -> {
            Intent intent = new Intent(this, ReportActivity.class);
            startActivity(intent);
        });

    }
}
