package com.tao.cache;

import org.springframework.cache.Cache;

/**
 * @author tyb
 * @Description
 * @create 2021-09-16 15:14
 */
public interface CacheHandler {

    Cache.ValueWrapper get(Object key);

    void put(Object key,Cache.ValueWrapper valueWrapper);
}
