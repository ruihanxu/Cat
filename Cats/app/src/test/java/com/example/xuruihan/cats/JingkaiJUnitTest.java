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
    private Report report;
    private String actual_date;
    private String expected_date;
    private static final int TIMEOUT = 200;

    @Before
    public void setup() {
        report = new Report();
    }

    /**
     * test for uninitialized a null report
     */
    @Test(timeout = TIMEOUT)
    public void testNullReport() {
        assertNull(report.getDate());
        assertNull(report.getAddress());
        assertNull(report.getBorough());
        assertNull(report.getCity());
        assertEquals(0, report.getKey());
    }

    /**
     * test for setting key for reports.
     */
    @Test(timeout = TIMEOUT)
    public void testSetKey() {
        report.setKey(1234);
        assertEquals(1234, report.getKey());
    }

    /**
     * test for setting address for reports.
     */
    @Test(timeout = TIMEOUT)
    public void testSetAddress() {
        report.setAddress("555 8th St NW");
        assertEquals("555 8th St NW", report.getAddress());
    }

    /**
     * test for setting city for reports.
     */
    @Test(timeout = TIMEOUT)
    public void testSetCity() {
        report.setCity("Atlanta");
        assertEquals("Atlanta", report.getCity());
    }

    /**
     * test for setting city for reports.
     */
    @Test(expected = RuntimeException.class)
    public void testSetException() {
        report.setKey(1/0);
    }
}
