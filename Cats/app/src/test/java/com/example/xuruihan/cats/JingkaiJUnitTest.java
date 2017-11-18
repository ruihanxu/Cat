package com.example.xuruihan.cats;

import com.example.xuruihan.cats.model.Report;
import com.example.xuruihan.cats.model.ReportManager;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Victor on 11/13/2017.
 */

public class JingkaiJUnitTest {
    private ArrayList<Report> expectedList;
    private ArrayList<Report> actualList;
    private static final int TIMEOUT = 200;

    @Before
    public void setup() {
        actualList = new ArrayList<>();
        expectedList = new ArrayList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testGetOneDateReports() {
        ReportManager.getInstance().getReportsByDate(actualList, "2015/10/01", "2015/10/02", null);
        assertEquals(82, actualList.size());
    }

    @Test(timeout = 60000)
    public void testGetOneYearReports() throws InterruptedException {
        ReportManager.getInstance().getReportsByDate(actualList, "2011", "2012", null);
        Thread.sleep(59900);
        assertEquals(10454, actualList.size());
    }

    @Test(timeout = 6000)
    public void testGetOneMonthReports() throws InterruptedException {
        ReportManager.getInstance().getReportsByDate(actualList, "2011/05", "2011/06", null);
        Thread.sleep(5900);
        assertEquals(1126, actualList.size());
    }
}
