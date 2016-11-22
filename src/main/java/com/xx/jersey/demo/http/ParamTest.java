package com.xx.jersey.demo.http;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hzxiongxin on 2016/11/17.
 * 传递参数
 */
@Path("param")
public class ParamTest {

    /**
     * 获取查询参数
     */
    @GET
    @Path("queryParam")
    public Response queryParam(@QueryParam("queryParam")String queryParam){
        return Response.ok(queryParam).build();
    }

    /**
     * 获取路径参数
     */
    @Path("{pathParam}")
    @GET
    public Response pathParam(@PathParam("pathParam") String pathParam){
        return Response.ok(pathParam).build();
    }

    /**
     * 获取请求头参数
     */
    @GET
    @Path("headerParam")
    public Response headerParam(@HeaderParam("headerParam")String headerParam){
        return Response.ok(headerParam).build();
    }

    /**
     * 获取分隔符参数
     */
    @GET
    @Path("matrixParam")
    public Response matrixParam(@MatrixParam("matrixParam")String matrixParam){
        return Response.ok(matrixParam).build();
    }

    /**
     * 获取表单参数
     */
    @POST
    @Path("formParam")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response formParam(@FormParam("formParam")String formParam){
        return Response.ok(formParam).build();
    }
}
