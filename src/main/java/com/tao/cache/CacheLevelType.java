package com.tao.cache;

import lombok.Getter;

import java.lang.annotation.*;
import java.util.Objects;

/**
 * @author tyb
 * @Description
 * @create 2021-09-16 14:47
 */
@Getter
public enum  CacheLevelType {
    LOCAL(0),

    REMOTE(1),

    MULTI(2);

    private Integer code;

    CacheLevelType(Integer code){
        this.code = code;
    }

    public CacheLevelType getCacheLevelType(Integer code){
        final CacheLevelType[] values = values();
        CacheLevelType result = CacheLevelType.MULTI;
        for (CacheLevelType cacheLevelType : values) {
            if (Objects.equals(code,cacheLevelType.getCode())){
                result = cacheLevelType;
            }
        }
        return result;
    }

}
