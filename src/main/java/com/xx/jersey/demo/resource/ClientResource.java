package com.xx.jersey.demo.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 第三方资源
 * Created by hzxiongxin on 2016/11/16.
 */
@Path("/client")
public class ClientResource {

    /**
     * 相当于请求创建第三方用户
     * @return 返回生成的第三方信息对象
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response post(@FormParam("domainName") String domainName, @FormParam("redirect_url") String redirect_url){
        //此处新增第三方信息
        return Response.ok().build();
    }
}
