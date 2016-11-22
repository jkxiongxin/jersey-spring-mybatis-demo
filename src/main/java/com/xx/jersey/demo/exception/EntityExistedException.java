package com.xx.jersey.demo.exception;

/**
 * Created by hzxiongxin on 2016/11/22.
 */
public class EntityExistedException extends Exception {

    public EntityExistedException(String entityName){
        super(entityName+" already existed!");
    }

}
