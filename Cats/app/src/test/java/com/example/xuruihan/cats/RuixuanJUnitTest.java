package com.example.xuruihan.cats;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import com.example.xuruihan.cats.model.ReportManager;
import com.example.xuruihan.cats.model.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Ruixuan on 11/6/17.
 */

public class RuixuanJUnitTest {
    private Map<String, String> expectedMap;
    private Map<String, String> actualMap;
    private Map<String, String> nullMap = new HashMap<>();
    private LoadingView callback;
    private static final int TIMEOUT = 200;
    private List<Report> reports;
    private  ReportManager manager;
    private Iterable<Report> nullReports;
    private Report report_1 = new Report(001, "2012/12/23/ 12:00:00 AM",
            null, null, null, null, null, null, null);
    private Report report_2 = new Report(002, "2012/12/23/ 12:00:00 AM",
            null, null, null, null, null, null, null);
    private Report report_3 = new Report(003, "2012/12/23/ 12:00:00 AM",
            null, null, null, null, null, null, null);
    private Report report_4 = new Report(004, "2015/12/23/ 12:00:00 AM",
            null, null, null, null, null, null, null);
    private Report report_5 = new Report(005, "2015/12/23/ 12:00:00 AM",
            null, null, null, null, null, null, null);
    private Report report_6 = new Report(006, "2017/12/23/ 12:00:00 AM",
            null, null, null, null, null, null, null);


    @Before
    public void setup() {
        manager = new ReportManager();
        expectedMap = new HashMap<>();
        reports = new ArrayList<>();
        reports.add(report_1);
        reports.add(report_2);
        reports.add(report_3);
        reports.add(report_4);
        reports.add(report_5);
        reports.add(report_6);

        expectedMap.put("2012", "3");
        expectedMap.put("2015", "2");
        expectedMap.put("2017", "1");

        actualMap = new HashMap<>();

    }

    @Test(timeout = TIMEOUT)
    public void testNullMap() {
        actualMap = manager.assignGraphData(nullReports, callback);
        assertEquals(nullMap, actualMap);
    }

    @Test(timeout = TIMEOUT)
    public void testHashMap() {
        actualMap = manager.assignGraphData(reports, callback);
        assertEquals(expectedMap, actualMap);
    }

    @Test(timeout = TIMEOUT)
    public void testSingleReport() {
        actualMap = manager.assignGraphData(reports, callback);
        assertEquals(1, actualMap.get("2017"));
    }

    @Test(timeout = TIMEOUT)
    public void testMultiReport() {
        actualMap = manager.assignGraphData(reports, callback);
        assertEquals(3, actualMap.get("2012"));
    }

    @Test(timeout = TIMEOUT)
    public void testKeySet() {
        actualMap = manager.assignGraphData(reports, callback);
        String[] expectedYear = {"2012", "2015", "2017"};
        assertEquals(expectedYear, actualMap.keySet());
    }

    @Test(timeout = TIMEOUT)
    public void testValues() {
        actualMap = manager.assignGraphData(reports, callback);
        String[] expectedReportAmount = {"3", "2", "1"};
        assertEquals(expectedReportAmount, actualMap.values());
    }


}
