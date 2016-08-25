package org.seckill.service;

import org.seckill.entity.User;
import java.util.Set;

/**
 * Created by LJ on 2016/7/29.
 */
public interface UserService {
    /**
     * Shiro的登录验证，通过用户名查询用户信息
     * @param username
     * @return
     */
    public User findUserByUsername(String username) ;

    /**
     * 根据账号查找角色名称
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) ;

    /**
     * 根据账号查找权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) ;
}
