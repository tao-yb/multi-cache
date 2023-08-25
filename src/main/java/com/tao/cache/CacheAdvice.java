package com.tao.cache;

import com.tao.util.SpelParser;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodClassKey;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author tyb
 * @Description
 * @create 2021-09-15 16:50
 */
public class CacheAdvice implements MethodInterceptor {

    @Autowired
    private MyCacheManger cacheManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Cache.ValueWrapper valueWrapper = findInCache(methodInvocation);
        if (Objects.nonNull(valueWrapper)) {
            return valueWrapper.get();
        }
        final String key = getKey(methodInvocation);
        Object invokeResult = methodInvocation.proceed();
        redisCache.put(key, invokeResult);
        return invokeResult;
    }

    private Cache.ValueWrapper findInCache(MethodInvocation methodInvocation) {
        final String key = getKey(methodInvocation);
        final CacheMetadata cacheMetadata = getCacheMetadata(methodInvocation);
        final CacheLevelType cacheLevelType = cacheMetadata.getCacheLevelType();
        Cache.ValueWrapper valueWrapper = null;
        switch (cacheLevelType) {
            case LOCAL:
                valueWrapper = findInLocalCache(methodInvocation);
                break;
            case REMOTE:
                valueWrapper = redisCache.get(key);
                break;
            case MULTI:
                valueWrapper = findInLocalCache(methodInvocation);
                if (Objects.isNull(valueWrapper)) {
                    valueWrapper = redisCache.get(key);
                    if (Objects.nonNull(valueWrapper.get())) {
                        return valueWrapper;
                    }
                }
            default:
                break;

        }
        return valueWrapper;

    }

    private Cache.ValueWrapper findInLocalCache(MethodInvocation methodInvocation) {
        Cache cache = getLocalCache(methodInvocation);
        return cache.get(getKey(methodInvocation));
    }

    private Cache getLocalCache(MethodInvocation methodInvocation) {
        final CacheMetadata cacheMetadata = getCacheMetadata(methodInvocation);
        final Cache cache = cacheManager.getCache(cacheMetadata.getMethod());
        if (Objects.nonNull(cache)) {
            return cache;
        }
        LocalGuavaCache localGuavaCache = new LocalGuavaCache(methodInvocation);
        cacheManager.putCache(cacheMetadata.getMethod(), localGuavaCache);
        return localGuavaCache;
    }

    private String getKey(MethodInvocation methodInvocation) {
        final CacheMetadata cacheMetadata = getCacheMetadata(methodInvocation);
        final Object[] arguments = methodInvocation.getArguments();
        ReflectiveMethodInvocation mi = (ReflectiveMethodInvocation) methodInvocation;
        final Method specificMethod = AopUtils.getMostSpecificMethod(methodInvocation.getMethod(),
                mi.getThis().getClass());
        final String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(specificMethod);
        return SpelParser.getValue(cacheMetadata.getKey(), parameterNames, arguments);
    }

    private CacheMetadata getCacheMetadata(MethodInvocation methodInvocation) {
        final CacheMetadata cacheMetadata = CachePointcut.getMethodMetadata(new MethodClassKey(methodInvocation.getMethod(),
                methodInvocation.getThis().getClass()));
        return cacheMetadata;
    }
}
