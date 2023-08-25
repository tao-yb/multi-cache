package com.tao.cache;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tyb
 * @Description
 * @create 2021-09-15 18:22
 */
public class MyCacheConfigurationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return getProxyImports();
    }

    private String[] getProxyImports() {
        List<String> result = new ArrayList<>(3);
        result.add(MyCacheProxyCreator.class.getName());
        return StringUtils.toStringArray(result);
    }
}
