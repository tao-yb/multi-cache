package com.tao.cache;

import org.springframework.cache.Cache;

/**
 * @author tyb
 * @Description
 * @create 2021-09-16 15:20
 */
public class LocalCacheHandler implements CacheHandler {
    @Override
    public Cache.ValueWrapper get(Object key) {
        return null;
    }

    @Override
    public void put(Object key, Cache.ValueWrapper valueWrapper) {

    }
}
