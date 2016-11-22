package com.xx.jersey.demo.http;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hzxiongxin on 2016/11/17.
 */
@Path("/http")
public class HeaderView {

    @GET
    @Path("headerView")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response view(@BeanParam HeaderBean header, @HeaderParam("user-agent")String userAgent){
        return Response.ok(header).build();
    }

}
