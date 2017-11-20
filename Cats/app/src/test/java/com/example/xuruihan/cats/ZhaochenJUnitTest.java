package com.example.xuruihan.cats;

import com.example.xuruihan.cats.model.UserItem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zluo on 11/16/17.
 */

public class ZhaochenJUnitTest {
    private UserItem userItem;
    private static final int TIMEOUT = 200;

    @Before
    public void setup() {
        userItem = new UserItem("50000000", "password", true);
    }

    @Test(timeout = TIMEOUT)
    public void testSetUser() {
        UserItem expectedUserItem = new UserItem("520520520", "password", true);
        userItem.setUser("520520520");
        assertEquals(expectedUserItem, userItem);
    }

    @Test(timeout = TIMEOUT)
    public void testSetNullUser() {
        int count = 0;
        try {
            userItem.setUser(null);
        } catch (IllegalArgumentException e) {
            count++;
        }
        assertEquals("setting null user should throw exception", 1, count);
    }

    @Test(timeout = TIMEOUT)
    public void testSetEmptyString() {
        userItem = new UserItem("50000000", "password", true);
        UserItem expectedUserItem = new UserItem("50000000", "password", true);

        userItem.setUser("");
        assertEquals(expectedUserItem, userItem);
    }
}
