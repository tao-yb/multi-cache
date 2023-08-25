package com.tao.config;

import com.tao.cache.DefaultCacheManager;
import com.tao.cache.MyCacheManger;
import com.tao.cache.RedisCache;
import com.tao.cache.anno.EnableMyCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMyCache
public class MyCacheConfig {

/*    @Bean( name = "springRedisCM")
    public CacheManager cacheManager(@Autowired RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }*/

    @Bean
    public RedisCache redisCache(){
        RedisCache redisCache = new RedisCache();
        redisCache.setName("redisCache");
        return redisCache;
    }


    @Bean
    @Qualifier(value = "multiCacheManager")
    public MyCacheManger cacheManager(@Autowired RedisTemplate redisTemplate){
        DefaultCacheManager cacheManager = new DefaultCacheManager();
        List<Cache> caches = new ArrayList<>();
        caches.add(redisCache());
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
