package com.tao.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleCacheManager;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tyb
 * @Description
 * @create 2021-09-16 18:52
 */
public class DefaultCacheManager extends SimpleCacheManager implements MyCacheManger {

    private static Map<Method, Cache> methodCacheMap = new ConcurrentHashMap<>(64);

    @Override
    public Cache getCache(String key) {
        return methodCacheMap.get(key);
    }

    @Override
    public synchronized void putCache(Method key,Cache cache) {
        methodCacheMap.computeIfAbsent(key,value->cache);
    }

    @Override
    public Cache getCache(Method name) {
        return methodCacheMap.get(name);
    }
}
