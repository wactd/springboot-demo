package com.dongly.configuration;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by dongly on 2017/6/22.
 */
public interface RedisUtils {

   <T> void set(String key, T t);

   <T> T get(String key, Class<T> clazz);

   <H> void putMap(String h, String hk, H hv);


    <H> void putMap(String h, Map<String, H> objectMap);

}
