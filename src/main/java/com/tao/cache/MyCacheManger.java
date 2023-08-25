package com.tao.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.lang.reflect.Method;

/**
 * @author tyb
 * @Description
 * @create 2021-09-17 10:55
 */
public interface MyCacheManger extends CacheManager {
    void putCache(Method key, Cache cache);

    Cache getCache(Method name);
}
