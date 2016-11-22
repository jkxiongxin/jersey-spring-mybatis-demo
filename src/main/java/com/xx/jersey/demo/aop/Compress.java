package com.xx.jersey.demo.aop;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by hzxiongxin on 2016/11/17.
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface Compress {
}
