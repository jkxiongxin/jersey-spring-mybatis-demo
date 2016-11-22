package com.xx.jersey.demo.Service;

import com.xx.jersey.demo.model.ThirdPart;

/**
 * Created by hzxiongxin on 2016/11/16.
 */
public interface ThirdPartService {

    void insert(ThirdPart thirdPart) throws Exception;

    ThirdPart findByName(String name);

    /**
     * 删除第三方
     * 首先判断当前人员是否有权限删除第三方
     * 然后判断资源是否存在
     * 最后执行删除操作
     * @param id 第三方的编号
     */
    void deleteThirdPart(int id) throws Exception;

}
