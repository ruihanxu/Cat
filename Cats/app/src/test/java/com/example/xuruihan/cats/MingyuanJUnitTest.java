package com.example.xuruihan.cats;

import com.example.xuruihan.cats.model.User;
import com.example.xuruihan.cats.model.UserItem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MingyuanJUnitTest {

    private User user;
    private UserItem userItem;
    private static final int TIMEOUT = 200;

    @Before
    public void setup() {
        userItem = new UserItem("50000000", "password", true);
        user = new User(userItem);
    }

    @Test(timeout = TIMEOUT)
    public void testSetUser() {
        UserItem expectedUserItem = new UserItem("555555555", "password", true);
        userItem.setUser("555555555");
        assertEquals(expectedUserItem, userItem);

        userItem = new UserItem("555555555", "password", true);
        User expectedUser = new User(userItem);
        user.setUser("555555555", "password");
        assertEquals(expectedUser, user);
    }

    @Test(timeout = TIMEOUT)
    public void testSetPassword() {
        UserItem expectedUserItem = new UserItem("50000000", "pass", true);
        userItem.setPass("pass");
        assertEquals(expectedUserItem, userItem);

        userItem = new UserItem("555555555", "password", true);
        User expectedUser = new User(userItem);
        user.setUser("555555555", "password");
        assertEquals(expectedUser, user);
    }

    @Test(timeout = TIMEOUT)
    public void testNullUserInfo() {
        int count = 0;
        try {
            user.setUser(null,  null);
        } catch (IllegalArgumentException e) {
            count++;
        }
        assertEquals("There is null exception : ", 1, count);
    }

    @Test(timeout = TIMEOUT)
    public void testSetEmptyUser() {
        userItem = new UserItem("500000001", "password", true);
        user = new User(userItem);
        UserItem expectedUserItem = new UserItem("500000001", "password", true);
        User expectedUser = new User(expectedUserItem);

        userItem.setUser("");
        user.setUser("", "");
        assertEquals(expectedUser, user);
    }
}
