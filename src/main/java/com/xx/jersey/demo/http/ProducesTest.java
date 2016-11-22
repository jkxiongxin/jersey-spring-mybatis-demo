package com.xx.jersey.demo.http;

import com.xx.jersey.demo.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hzxiongxin on 2016/11/17.
 */
@Path("produces")
public class ProducesTest {

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response produces(){
        User user = new User();
        user.setPassword("12345");
        user.setId(1);
        user.setName("熊鑫");
        return Response.ok(user).build();
    }

}
