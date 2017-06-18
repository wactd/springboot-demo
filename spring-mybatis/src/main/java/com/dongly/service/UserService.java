package com.dongly.service;

import com.dongly.model.UserPo;
import com.github.pagehelper.PageInfo;

/**
 * Created by tiger on 2017/6/19.
 */
public interface UserService {

    PageInfo<UserPo> selectPageList(Integer pageNo, Integer pageSize);

    Integer addUser(UserPo userPo);

}
