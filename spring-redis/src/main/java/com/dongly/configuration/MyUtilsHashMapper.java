package com.dongly.configuration;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.redis.hash.HashMapper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dongly on 2017/6/23.
 */
public class MyUtilsHashMapper<T> implements HashMapper<T, String, Object> {


    public static <R> MyUtilsHashMapper<R> create(Class<R> type) {
        return new MyUtilsHashMapper<R>(type);
    }

    private Class<T> type;

    public MyUtilsHashMapper(Class<T> type) {
        this.type = type;
    }

    @Override
    public Map<String, Object> toHash(T object) {
        try {
            Map<String, String> map = BeanUtils.describe(object);
            Map<String, Object> result = new LinkedHashMap<>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue() != null) {
                    result.put(entry.getKey(), entry.getValue());
                }
            }
            return result;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot describe object " + object, ex);
        }
    }

    @Override
    public T fromHash(Map<String, Object> hash) {
        T instance = org.springframework.beans.BeanUtils.instantiate(type);
        try {
            BeanUtils.populate(instance, hash);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return instance;
    }

}
