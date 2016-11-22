package com.xx.jersey.demo.Service.impl;


import com.xx.jersey.demo.Service.UserService;
import com.xx.jersey.demo.dao.UserMapper;
import com.xx.jersey.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hzxiongxin on 2016/11/16.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    public UserMapper userMapper;

    public User getUser(int id) {
        return userMapper.getUser(id);
    }

    @Transactional
    public void addUser(User user) {
        userMapper.insert(user);
    }
}
