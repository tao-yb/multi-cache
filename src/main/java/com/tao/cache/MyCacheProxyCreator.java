package com.tao.cache;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;

import java.util.List;

/**
 * @author tyb
 * @Description
 * @create 2021-09-15 18:00
 */
public class MyCacheProxyCreator extends AbstractAdvisorAutoProxyCreator {
    @Override
    protected List<Advisor> findEligibleAdvisors(Class<?> beanClass, String beanName) {
        return super.findEligibleAdvisors(beanClass, beanName);
    }

    @Override
    protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
        return super.wrapIfNecessary(bean, beanName, cacheKey);
    }
}
