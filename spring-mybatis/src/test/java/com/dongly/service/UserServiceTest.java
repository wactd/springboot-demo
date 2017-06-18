package com.dongly.service;

import com.alibaba.fastjson.JSON;
import com.dongly.SpringMybatisApplicationTests;
import com.dongly.mapper.UserMapper;
import com.dongly.model.UserPo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by tiger on 2017/6/19.
 */

@Transactional
public class UserServiceTest extends SpringMybatisApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void selectPageList() throws Exception {


        System.out.println(JSON.toJSONString(userService.selectPageList(1,2)));
    }

    @Test
    public void addUser() {
        UserPo po = new UserPo();
        po.setUsername("小黑");
        po.setBirthday(new Date());
        po.setAge(20);
        userService.addUser(po);
    }

}