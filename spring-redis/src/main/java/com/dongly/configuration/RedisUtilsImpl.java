package com.dongly.configuration;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dongly on 2017/6/22.
 */
@Component
public class RedisUtilsImpl implements RedisUtils {

    @Autowired
    private ValueOperations<String, String> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;


    @Override
    public <T> void set(String key, T t) {

    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        return null;
    }

    @Override
    public <H> void putMap(String h, String hk, H hv) {

    }

    @Override
    public <H> void putMap(String h, Map<String, H> objectMap) {

    }
}
