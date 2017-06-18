package com.dongly.mapper;

import com.dongly.SpringMybatisApplicationTests;
import com.dongly.model.UserPo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by tiger on 2017/6/19.
 */

// 添加此注解自动回滚
@Transactional
public class UserMapperTest extends SpringMybatisApplicationTests{

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectPageList() throws Exception {
    }

    @Test
    public void insert() throws Exception {
        UserPo po = new UserPo();
        po.setUsername("小黑123");
        po.setBirthday(new Date());
        po.setAge(20);
        userMapper.insert(po);
    }

}