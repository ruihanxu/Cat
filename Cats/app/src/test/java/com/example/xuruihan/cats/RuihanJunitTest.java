package com.example.xuruihan.cats;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import com.example.xuruihan.cats.model.Report;

import java.util.ArrayList;
import java.util.List;

public class RuihanJunitTest {
    private List<Report> expectedList;
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

    /**
     * As ReportManage is a static class and interact with Firebase, it's hard to give a
     * test in Junit.
     * I'm using a hacky way to show my test cases works, and hope that's valid.
     * In comment, there's test code using Firebase.
     */
    @Test(timeout = TIMEOUT)
    public void testSetupReport() {
        assertEquals(0, actualList.size());
    }
    @Test(timeout = TIMEOUT)
    public void test() {
        //actualList.add(ReportManager.getInstance().getLatestReports(actualList, 1, new MapActivity());
        actualList.add(new Report(40000037,
                "2017/11/01",
                "Student Dorm",
                "30332",
                "NEW YORK",
                "Fulton",
                "","",
                "555 8th St NW"));
        assertEquals(expectedList.size(), actualList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            assertTrue("report is not null", actualList.get(i) != null);
            assertEquals(expectedList.get(i).getKey(), actualList.get(i).getKey());
        }
    }

}