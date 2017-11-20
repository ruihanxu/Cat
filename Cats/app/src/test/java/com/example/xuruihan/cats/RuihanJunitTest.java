package com.example.xuruihan.cats;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import com.example.xuruihan.cats.model.Report;

public class RuihanJunitTest {
    private Report report;
    private String actual_date;
    private String expected_date;
    private static final int TIMEOUT = 200;
    //private static ReportManager;

    @Before
    public void setup() {
        report = new Report(40000037,
                "2017/11/01",
                "Student Dorm",
                "30332",
                "NEW YORK",
                "Fulton",
                "","",
                "555 8th St NW");
        expected_date = "2017/11/01";
        actual_date = "1998/12/02";
    }

    /**
     * test for uninitialized report
     */
    @Test(timeout = TIMEOUT)
    public void testSetupReport() {
        assertEquals(report.getDate(), expected_date);
    }

    /**
     * test for valid set of date
     * @throws IllegalAccessException if arg is empty
     */
    @Test(timeout = TIMEOUT)
    public void test() throws IllegalAccessException {
        report.setDate("1998/12/02");
        expected_date = report.getDate();
        //actualList.add(ReportManager.getInstance().getLatestReports(actualList, 1, new MapActivity());
        /*actualList.add(new Report(40000037,
                "2017/11/01",
                "Student Dorm",
                "30332",
                "NEW YORK",
                "Fulton",
                "","",
                "555 8th St NW"));*/
        assertEquals(expected_date, actual_date);
    }

    /**
     * test should throw exceptions
     */
    @Test(expected = IllegalArgumentException.class)
    public void testException() throws IllegalArgumentException {
        report.setDate("");
    }
}