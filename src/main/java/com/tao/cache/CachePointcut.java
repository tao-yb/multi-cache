package com.tao.cache;

import com.tao.cache.anno.MyCache;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.MethodClassKey;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tyb
 * @Description
 * @create 2021-09-15 16:51
 */

public class CachePointcut extends StaticMethodMatcherPointcut {

    private static final Map<MethodClassKey, CacheMetadata> methodClassKeyCacheMetadataMap = new ConcurrentHashMap<>(32);

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        final Method specificMethod = AopUtils.getMostSpecificMethod(method, aClass);
        final MyCache annotation = AnnotationUtils.findAnnotation(specificMethod, MyCache.class);
        if (null == annotation){
            return  false;
        }
        final String[] cacheNames = annotation.cacheNames();
        final CacheMetadata cacheMetadata =
                CacheMetadata.builder()
                        .cacheNames(cacheNames)
                        .key(annotation.key())
                        .cacheLevelType(annotation.cacheLevelType())
                        .method(specificMethod)
                        .build();
        MethodClassKey key = getMethodKey(method,aClass);
        methodClassKeyCacheMetadataMap.computeIfAbsent(key, (value) -> cacheMetadata);
        return Objects.nonNull(cacheMetadata);
    }

    private MethodClassKey getMethodKey(Method method,Class targetClass){
        return new MethodClassKey(method, targetClass);
    }

    public static CacheMetadata getMethodMetadata(MethodClassKey key){
        return methodClassKeyCacheMetadataMap.get(key);
    }

}
