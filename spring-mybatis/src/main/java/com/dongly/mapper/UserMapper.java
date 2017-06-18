package com.dongly.mapper;

import com.dongly.model.UserPo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tiger on 2017/6/19.
 */

@Repository
public interface UserMapper {


    @Select("SELECT user_id userId, username, birthday, age FROM t_user")
    List<UserPo> selectPageList();

    Integer insert(UserPo userPo);

}
