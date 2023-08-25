package com.tao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author tyb
 * @Description
 * @create 2021-09-14 11:30
 */
@Component
@ComponentScan(basePackages = "com.tao",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class)
        })
public class RootConfig {
}
