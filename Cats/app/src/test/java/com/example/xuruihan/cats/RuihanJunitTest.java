package com.example.xuruihan.cats;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import com.example.xuruihan.cats.model.ReportManager;
import com.example.xuruihan.cats.model.Report;

import java.util.ArrayList;

public class RuihanJunitTest {
    private ArrayList<Report> expectedList;
    private ArrayList<Report> actualList;
    private static final int TIMEOUT = 200;

    @Before
    public void setup() {
        expectedList = new ArrayList<>();
        expectedList.add(new Report(40000037,
                "2017/11/01",
                "Student Dorm",
                "30332",
                "NEW YORK",
                "Fulton",
                "","",
                "555 8th St NW"));
        actualList = new ArrayList<>();
    }
    @Test(timeout = TIMEOUT)
    public void testGetZeroReports() {
        ReportManager.getInstance().getLatestReports(actualList, 0, new MapActivity());
        assertEquals(0, actualList.size());
    }
    @Test(timeout = TIMEOUT)
    public void testGetLatestReports() {
        ReportManager.getInstance().getLatestReports(actualList, 1, new MapActivity());
        assertEquals(expectedList.size(), actualList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            assertTrue("report is null", actualList.get(i) != null);
            assertEquals(expectedList.get(i).getKey(), actualList.get(i).getKey());
        }
    }
    @Test(expected = IllegalArgumentException.class)
    public void testNegOneArrException() {
        ReportManager.getInstance().getLatestReports(actualList, -1, new MapActivity());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testNullArrException() {
        ReportManager.getInstance().getLatestReports(null, -1, new MapActivity());
    }
}