import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static junit.framework.Assert.assertEquals;

/**
 * Created by hzxiongxin on 2016/11/22.
 */
public class ThirdPartRegistTest {

    private static Client client;

    private static final String ROOT_URL = "http://localhost:8080/rest";

    private static final String PATH = "thirdPart";

    @BeforeClass
    public static void initClient(){
        client = ClientBuilder.newClient();
    }

    public WebTarget getTarget(){
        return client.target(ROOT_URL).path(PATH);
    }

    public Response post(Form form){
        return getTarget().request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
    }

    public Response assertPostStatus(Form form,int status){
        Response response = post(form);
        int responseStatus = response.getStatus();
        assertEquals(status,responseStatus);
        return response;
    }

    /**
     * 参数测试
     */
    @Test
    public void parameterTest(){
        /*
            用户名不合法
         */
        Form form = new Form();
        form.param("name","123");
        form.param("redirect_url","http://www.baidu.com");
        assertPostStatus(form,400);


        Form form1 = new Form();
        form1.param("name","xx0123456789xxxxx");
        form1.param("redirect_url","http://www.baidu.com");
        assertPostStatus(form1,400);

        /*
            重定向地址不存在
         */
        Form form2 = new Form();
        form2.param("name","xx123");
        form2.param("redirect_url","http://www.xiongxin.com/test");
        assertPostStatus(form2,400);

        //正确的参数
        Form form3 = new Form();
        form3.param("name","xx123");
        form3.param("redirect_url","http://www.baidu.com");
        Response response = assertPostStatus(form3,201);
        String location = response.getHeaderString("location");
        System.out.println(location);
        //插入成功后删除
        WebTarget target = client.target(location);
        Response response1 = target.request().delete();
        assertEquals(205,response1.getStatus());
    }

}
