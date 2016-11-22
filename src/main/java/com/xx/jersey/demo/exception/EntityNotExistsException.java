package com.xx.jersey.demo.exception;

/**
 * Created by hzxiongxin on 2016/11/22.
 */
public class EntityNotExistsException extends Exception {

    public EntityNotExistsException(String entityName){
        super(entityName+" not exists!");
    }

}
