package com.tool.api.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tool.api.BaseTest;
import com.tool.api.entity.User;

public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testQueryById() throws Exception {
        String userId = "abcddsssagafafa";
        User user = userDao.findUserById(userId);
        System.out.println(user);
    }

}