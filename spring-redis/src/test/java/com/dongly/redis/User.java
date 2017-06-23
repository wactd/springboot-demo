package com.dongly.redis;

import com.dongly.configuration.MyUtilsHashMapper;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by dongly on 2017/6/22.
 */
public class User implements Serializable{

    public static void main(String[] args) {
        User user = new User();
        user.setAge(11);
        MyUtilsHashMapper<User> mapper = MyUtilsHashMapper.create(User.class);

        Map<String, Object> toHash = mapper.toHash(user);
        User user1 = mapper.fromHash(toHash);

        System.out.println(user1);

    }

    private static final long serialVersionUID = -3656338919125584541L;
    private Long id;
    private String username;
    private Date birthday;
    private Integer age;
    private Boolean flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
