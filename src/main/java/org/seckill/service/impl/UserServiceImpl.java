package org.seckill.service.impl;

import org.seckill.dao.UserDao;
import org.seckill.entity.User;
import org.seckill.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by LJ on 2016/7/29.
 */
@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao ;

    public User findUserByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        return user;
    }


    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }


    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }
}
