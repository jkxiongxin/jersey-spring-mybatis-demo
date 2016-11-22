package com.xx.jersey.demo.resource;

import com.xx.jersey.demo.Service.UserService;
import com.xx.jersey.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hzxiongxin on 2016/11/15.
 */
@Path("/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response getUser(@DefaultValue("1")@PathParam("id") Integer id){
        //查询用户
        User user = userService.getUser(id);
        return Response.ok(user).build();//返回user对象
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postUser(@FormParam("username")String username,@FormParam("password")String password){
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        userService.addUser(user);
        return Response.status(203).build();
    }

    @DELETE
    public Response deleteUser(){
        //删除用户


        return Response.ok().build();
    }

}
