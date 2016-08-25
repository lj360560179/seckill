package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.seckill.entity.User;

import javax.annotation.Resource;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by LJ on 2016/7/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Test
    public void findUserByUsername() throws Exception {
        String username = "lj";
        User user = userDao.findUserByUsername(username);
        System.out.print(user);
    }

    @Test
    public void findRoles() throws Exception {
        String username = "aaa";
        Set<String> set = userDao.findRoles(username);
        for (String string : set){
            System.out.print(string);
        }

    }

    @Test
    public void findPermissions() throws Exception {
        String username = "lj";
        Set<String> set = userDao.findPermissions(username);
        for (String string : set){
            System.out.print(string);
        }
    }

}