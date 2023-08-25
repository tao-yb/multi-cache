package com.tao.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.SneakyThrows;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author tyb
 * @Description
 * @create 2021-09-16 15:30
 */
public class LocalGuavaCache implements Cache {

    LoadingCache<Object, Object> cache;

    private MethodInvocation methodInvocation;

    public void setMethodInvocation(MethodInvocation methodInvocation) {
        this.methodInvocation = methodInvocation;
    }

    public LocalGuavaCache(MethodInvocation methodInvocation) {
        cache = CacheBuilder.newBuilder().concurrencyLevel(Runtime.getRuntime().availableProcessors()).build(
                new CacheLoader<Object, Object>() {
                    @SneakyThrows
                    @Override
                    public Object load(Object o) throws Exception {
                        return methodInvocation.proceed();
                    }
                }
        );
    }

    private LoadingCache<Object, Object> buildCache(MethodInvocation methodInvocation) {
        return CacheBuilder.newBuilder().concurrencyLevel(Runtime.getRuntime().availableProcessors()).build(
                new CacheLoader<Object, Object>() {
                    @SneakyThrows
                    @Override
                    public Object load(Object o) throws Exception {
                        return methodInvocation.proceed();
                    }
                }
        );
    }

    public Object findInCache(Object key) throws ExecutionException {
        return cache.get(key);
    }



    @Override
    public String getName() {
        return LocalGuavaCache.class.getSimpleName();
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @SneakyThrows
    @Override
    public ValueWrapper get(Object key) {
        final Object value = cache.get(key);
        ValueWrapper result = Objects.isNull(value) ? null : new SimpleValueWrapper(value);
        return result;
    }

    @Override
    public <T> T get(Object key, Class<T> aClass) {
        final ValueWrapper valueWrapper = get(key);
        return (T) valueWrapper;
    }

    @Override
    public <T> T get(Object key, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        cache.put(key, value);
    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public void clear() {

    }
}
