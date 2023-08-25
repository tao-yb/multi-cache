package com.tao.service;

import com.tao.cache.CacheLevelType;
import com.tao.cache.anno.MyCache;
import com.tao.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author tyb
 * @Description
 * @create 2021-09-14 14:56
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    @MyCache(value = "local",key = "'u'+#id",cacheLevelType = CacheLevelType.LOCAL)
    public User getUser(Integer id) {
        User user = new User();
        user.setId(id);
        user.setAge(10);
        user.setName("tao");
        return user;
    }
}
