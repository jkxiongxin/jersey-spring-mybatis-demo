package com.xx.jersey.demo.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

/**
 * Created by hzxiongxin on 2016/11/16.
 */
public class RequestFilterS implements ContainerRequestFilter {
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("filter 1 执行");
    }
}
