package com.tao.cache;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cache.CacheManager;

import java.lang.reflect.Method;

/**
 * @author tyb
 * @Description
 * @create 2021-09-15 17:33
 */
@Getter
@Setter
@Builder
public class CacheMetadata {
    private Method method;

    private String key;

    private Integer ttl;

    private String[] cacheNames;

    private CacheManager cacheManager;

    private CacheLevelType cacheLevelType;
}
