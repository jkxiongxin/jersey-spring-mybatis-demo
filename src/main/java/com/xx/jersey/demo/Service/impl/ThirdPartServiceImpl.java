package com.xx.jersey.demo.Service.impl;

import com.xx.jersey.demo.Service.ThirdPartService;
import com.xx.jersey.demo.dao.ThirdPartMapper;
import com.xx.jersey.demo.exception.EntityExistedException;
import com.xx.jersey.demo.exception.EntityNotExistsException;
import com.xx.jersey.demo.model.ThirdPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by hzxiongxin on 2016/11/16.
 */
@Service
public class ThirdPartServiceImpl implements ThirdPartService {

    private final static Logger logger = LoggerFactory.getLogger(ThirdPartService.class);
    @Autowired
    private ThirdPartMapper thirdPartMapper;

    @Override
    @Transactional
    public void insert(ThirdPart thirdPart) throws Exception {
        //验证用户名是否已存在
        if (isThirdPartExists(thirdPart.getName())){
            throw new EntityExistedException("ThirdPart");
        }
        //完善第三方对象
        fillInThirdPart(thirdPart);
        thirdPartMapper.insert(thirdPart);
    }

    private boolean isThirdPartExists(String name){
        return findByName(name)!=null;
    }

    private void fillInThirdPart(ThirdPart thirdPart){
        //生成clientId和clientSecret
        thirdPart.setClientId("123");
        thirdPart.setClientSecret("12345");
        Date createTime = new Date();
        thirdPart.setCreateTime(createTime);
    }

    @Override
    public ThirdPart findByName(String name) {
        return thirdPartMapper.findByName(name);
    }

    @Override
    @Transactional
    public void deleteThirdPart(int id) throws Exception{
        //判断当前人员是否有删除权限

        //判断资源是否存在
        if(isThirdPartExists(id)){
            throw new EntityNotExistsException("ThirdPart");
        }
        //执行删除操作
        thirdPartMapper.delete(id);
        logger.debug("delete third part id is "+id);
    }

    private boolean isThirdPartExists(Integer id){
        return thirdPartMapper.findById(id)!=null;
    }
}
