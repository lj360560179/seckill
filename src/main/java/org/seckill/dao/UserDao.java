package org.seckill.dao;

import org.seckill.entity.User;
import java.util.Set;

/**
 * Created by LJ on 2016/7/29.
 */
public interface UserDao {
    User findUserByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
