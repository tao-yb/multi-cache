package com.tao.cache;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.beans.factory.BeanFactory;

/**
 * @author tyb
 * @Description
 * @create 2021-09-15 20:26
 */
public class MyCacheAdvisor extends AbstractBeanFactoryPointcutAdvisor {


    private CachePointcut cachePointcut = new CachePointcut();



    @Override
    public void setAdvice(Advice advice) {
        super.setAdvice(advice);
    }

    @Override
    public Pointcut getPointcut() {
        return this.cachePointcut;
    }

}
