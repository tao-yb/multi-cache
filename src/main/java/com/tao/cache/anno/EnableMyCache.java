package com.tao.cache.anno;

import com.tao.cache.MyCacheConfigurationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author tyb
 * @Description
 * @create 2021-09-15 18:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Documented
@Import({MyCacheConfigurationSelector.class})
public @interface EnableMyCache {
}
