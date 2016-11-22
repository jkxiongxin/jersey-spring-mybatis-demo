package com.xx.jersey.demo.Service;

import com.xx.jersey.demo.model.User;

/**
 * Created by hzxiongxin on 2016/11/16.
 */
public interface UserService {
    User getUser(int id);
    void addUser(User user);
}
