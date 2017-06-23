package com.dongly.redis;

import com.dongly.SpringRedisApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by dongly on 2017/6/22.
 */
public class TemplateTest extends SpringRedisApplicationTests {

    @Autowired
    private RedisTemplate<String, String> template;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Test
    public void testUser() throws Exception {




    }

}
