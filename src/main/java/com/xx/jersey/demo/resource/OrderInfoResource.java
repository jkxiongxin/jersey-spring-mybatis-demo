package com.xx.jersey.demo.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by hzxiongxin on 2016/11/17.
 */
@Path("orderInfo")
public class OrderInfoResource {

    @GET
    @Path("{id}")
    public Response get(@PathParam("id")Integer id){
        return Response.ok(id).build();
    }

}
