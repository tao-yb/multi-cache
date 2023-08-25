package com.tao.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tyb
 * @Description
 * @create 2021-09-14 10:55
 */
@Getter
@Setter
public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
}
