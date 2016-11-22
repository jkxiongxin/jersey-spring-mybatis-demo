package com.xx.jersey.demo.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

/**
 * Created by hzxiongxin on 2016/11/16.
 */
public class RequestFilter implements ContainerRequestFilter {

    public void filter(ContainerRequestContext requestContext) throws IOException {
        //获取request拿到请求的client_id和client_secret
    }

}
