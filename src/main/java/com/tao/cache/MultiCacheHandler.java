package com.tao.cache;

import org.springframework.cache.Cache;

/**
 * @author tyb
 * @Description
 * @create 2021-09-16 15:23
 */
public class MultiCacheHandler implements CacheHandler {

    private CacheHandler cacheHandler;

    public MultiCacheHandler(CacheHandler cacheHandler) {
        this.cacheHandler = cacheHandler;
    }

    @Override
    public Cache.ValueWrapper get(Object key) {
        return cacheHandler.get(key);
    }

    @Override
    public void put(Object key, Cache.ValueWrapper valueWrapper) {
        cacheHandler.put(key, valueWrapper);
    }
}
