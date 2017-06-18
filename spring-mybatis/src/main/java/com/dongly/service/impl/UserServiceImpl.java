package com.dongly.service.impl;

import com.dongly.mapper.UserMapper;
import com.dongly.model.UserPo;
import com.dongly.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tiger on 2017/6/19.
 */

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public PageInfo<UserPo> selectPageList(Integer pageNo, Integer pageSize) {

        PageInfo<UserPo> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> userMapper.selectPageList());

        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Integer addUser(UserPo userPo) {
        userMapper.insert(userPo);
        userMapper.insert(userPo);
        return null;
    }
}
