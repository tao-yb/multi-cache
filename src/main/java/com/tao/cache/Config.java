package com.tao.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tyb
 * @Description
 * @create 2021-09-15 16:46
 */
@Configuration
public class Config {
    @Bean
    public MyCacheAdvisor myCacheAdvisor(){
        MyCacheAdvisor myCacheAdvisor = new MyCacheAdvisor();
        myCacheAdvisor.setAdvice(cacheAdvice());
        return myCacheAdvisor;
    }

    @Bean
    public CacheAdvice cacheAdvice(){
        return new CacheAdvice();
    }
}
