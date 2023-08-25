package com.tao.cache;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.Callable;

public class RedisCache implements Cache {

    private String name;

    @Autowired
    private RedisTemplate redisTemplate;

    public void setName(String name) {
        this.name = name;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public ValueWrapper get(Object key) {
        System.out.println("------缓存获取-------"+key.toString());
        String key1 = key.toString();
        Object obj ;
        obj = redisTemplate.execute((RedisCallback<Object>) (connection)->{
            final byte[] bytes = connection.get(key1.getBytes());
            if (null !=  bytes){
                return SerializationUtils.deserialize(bytes);
            }
            return null;
        });
        ValueWrapper result = null != obj? new SimpleValueWrapper(obj):null;
        System.out.println("------获取到内容-------"+(result != null ? result.get():""));
        return result;
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        System.out.println("-------加入缓存-----key----:"+key + "value----:"+value);
        final long liveTime = 86400;
        redisTemplate.execute((RedisCallback<Long>)connection->{
            connection.set(key.toString().getBytes(),String.valueOf(value).getBytes());
            connection.expire(key.toString().getBytes(),liveTime);
            return 1L;
        });
    }

    @Override
    public void evict(Object key) {
        System.out.println("-------删除缓存-----key----:"+key );
        redisTemplate.execute((RedisCallback<Long>)connection ->{
           return connection.del(key.toString().getBytes());
        });

    }

    @Override
    public void clear() {
        System.out.println("-------緩存清理------");
        redisTemplate.execute((RedisCallback<String>) connection->{
            connection.flushDb();
            return "ok";
        });
    }
}
