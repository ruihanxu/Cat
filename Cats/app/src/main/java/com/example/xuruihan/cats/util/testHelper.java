package com.example.xuruihan.cats.util;

import com.example.xuruihan.cats.MapActivity;
import com.example.xuruihan.cats.model.Report;
import com.example.xuruihan.cats.model.ReportManager;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by xuruihan on 2017/11/4.
 */

public class testHelper {
    public ArrayList<Report> setLatestReports(int amount) {
        ArrayList<Report> list = new ArrayList<>();
        ReportManager.getInstance().getLatestReports(list, amount, new MapActivity());
        return list;
    }
}
