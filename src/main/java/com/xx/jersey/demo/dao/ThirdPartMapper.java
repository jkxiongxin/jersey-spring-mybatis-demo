package com.xx.jersey.demo.dao;

import com.xx.jersey.demo.model.ThirdPart;

/**
 * Created by hzxiongxin on 2016/11/18.
 */
public interface ThirdPartMapper {

    int insert(ThirdPart thirdPart);

    ThirdPart findByName(String name);

    int delete(Integer id);

    ThirdPart findById(Integer id);

}
