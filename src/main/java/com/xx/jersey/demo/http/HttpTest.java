package com.xx.jersey.demo.http;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by hzxiongxin on 2016/11/17.
 */
@Path("/http")
public class HttpTest {

    /**
     * 测试GET方法
     */
    @GET
    public Response get(){
        return Response.ok("实际内容").build();
    }

    /**
     * 测试POST方法
     */
    @POST
    public Response post(){
        return Response.ok("post").build();
    }

    /**
     * 测试PUT方法
     */
    @PUT
    public Response put(){
        return Response.ok("put").build();
    }

    /**
     * 测试DELETE方法
     */
    @DELETE
    public Response delete(){
        return Response.ok("delete").build();
    }

}
