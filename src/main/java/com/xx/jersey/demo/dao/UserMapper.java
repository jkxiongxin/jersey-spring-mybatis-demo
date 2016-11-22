package com.xx.jersey.demo.dao;

import com.xx.jersey.demo.model.User;

/**
 * Created by hzxiongxin on 2016/11/16.
 */
public interface UserMapper {

    /**
     * 根据用户id来获取用户
     * @param id 用户id
     * @return 用户对象
     */
    User getUser(int id);

    /**
     * 新增用户
     * @param user 用户对象
     * @return 数据库受影响条数
     */
    int insert(User user);

}
