package com.xx.jersey.demo.resource;

import com.xx.jersey.demo.Service.ThirdPartService;
import com.xx.jersey.demo.exception.EntityExistedException;
import com.xx.jersey.demo.exception.EntityNotExistsException;
import com.xx.jersey.demo.model.ThirdPart;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hzxiongxin on 2016/11/18.
 */
@Path("thirdPart")
public class ThirdPartResource {

    @Autowired
    private ThirdPartService thirdPartService;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response thirdPartRegister(@FormParam("name")String name,@FormParam("redirect_url")String redirectUrl){
        //验证数据
        if(!(matchName(name)&&pingRedirectUrl(redirectUrl))){
            return Response.status(400).build();
        }
        ThirdPart thirdPart = new ThirdPart();
        thirdPart.setName(name);
        thirdPart.setRedirectUrl(redirectUrl);
        //调用注册服务
        try {
            thirdPartService.insert(thirdPart);
        } catch (EntityExistedException e) {
            e.printStackTrace();
            return Response.status(400).tag("ThirdPart already exists!").build();
        } catch (Exception e1){
            e1.printStackTrace();
            return Response.status(500).build();//未知错误
        }
        //新增第三方到数据库
        return Response.created(UriBuilder.fromPath("thirdPart/"+thirdPart.getId()).build()).build();//已处理但是没有返回实体
    }

    private boolean matchName(String name){
        String patternStr = "^[a-zA-Z][a-zA-Z0-9]{1,15}";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean pingRedirectUrl(String redirectUrl){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(redirectUrl);
        Response response = webTarget.request().options();
        if(!(response.getStatus() == 200)){
            return false;
        }
        return true;
    }

    @GET
    public Response getThirdPart(@QueryParam("name")String name){
        //通过name获取第三方信息
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteThirdPart(@PathParam("id")Integer id){
        //输入验证，大于零的整数
        if(id<=0){
            return Response.status(400).tag("id should be above 0!").build();
        }
        //执行删除服务
        try {
            thirdPartService.deleteThirdPart(id);
        }catch (EntityNotExistsException exception){
            return Response.status(410).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(205).build();//返回205，通知客户端刷新视图
    }

}
