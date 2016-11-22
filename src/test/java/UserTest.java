
import com.xx.jersey.demo.model.User;
import static junit.framework.TestCase.assertEquals;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.rx.Rx;
import org.glassfish.jersey.client.rx.RxClient;
import org.glassfish.jersey.client.rx.RxWebTarget;
import org.glassfish.jersey.client.rx.rxjava.RxObservable;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import rx.Observable;
import rx.functions.Func1;

import javax.print.attribute.standard.Destination;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by hzxiongxin on 2016/11/16.
 */

public class UserTest{

    public final String baseUri = "http://localhost:9898/rest/";

    private static Client client;

    @BeforeClass
    public static void setupClass(){
        client = ClientBuilder.newClient();
    }
    @AfterClass
    public static void turnDownClass(){
        client.close();
    }

    @Test
    public void testGetUser(){
        Client client = ClientBuilder.newClient();//新建客户端
        WebTarget target = client.target(baseUri).path("user");
        target.queryParam("id",1);
        Response response = target.request().get();
        String responseEntity = response.readEntity(String.class);
        System.out.println(responseEntity);
        response.close();
    }

    public void get(String path){
        WebTarget target = client.target(baseUri).path(path);
        Response response = target.request().get();
        String responseEntity = response.readEntity(String.class);
        System.out.println(responseEntity);
        response.close();
    }

    @Test
    public void testHeaderView(){
        get("http/headerView");
    }

    private WebTarget getTarget(String path){
        return client.target(baseUri).path(path);
    }

    private void print(Response response){
        String responseEntity = response.readEntity(String.class);
        Integer status = response.getStatus();
        System.out.println(status);
        System.out.println(responseEntity);
    }

    private void post(String path, Form form){
        WebTarget target = getTarget(path);
        Response response = target.request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        print(response);
    }

    private void put(String path, Form form){
        WebTarget target = getTarget(path);
        Response response = target.request().put(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        print(response);
    }

    private void delete(String path){
        WebTarget target = getTarget(path);
        Response response = target.request().delete();
        print(response);
    }

    @Test
    public void testPostUser(){
        Form form = new Form();
        form.param("username","xx");
        form.param("password","12345");
        post("user",form);
    }

    @Test
    public void uploadFile(){
        final ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(MultiPartFeature.class);
        //jersey客户端上传文件
        Client client = ClientBuilder.newClient(clientConfig);
        FormDataMultiPart formData = new FormDataMultiPart();
        MultiPart entity = formData.bodyPart(new FileDataBodyPart("file",new File("d:/1.txt")));
        WebTarget target = client.target(baseUri+"http/file");
        Response response = target.request().post(Entity.entity(entity,MediaType.MULTIPART_FORM_DATA_TYPE));
        String responseEntity = response.readEntity(String.class);
        Integer status = response.getStatus();
        System.out.println(status);
        System.out.println(responseEntity);
        response.close();
    }

    @Test
    public void testHttp(){
        String uri = "http";
        get(uri);
        Form form = new Form();
        post(uri,form);
        put(uri,form);
        delete(uri);
    }

    @Test
    public void testAsc(){
        //同步调用测试
        //开始的时候
        long begin=System.currentTimeMillis();
        get("user/1");
        for(int i = 0; i < 1000; i++){
            get("orderInfo/"+i);
        }
        //结束的时候
        System.out.println((System.currentTimeMillis()-begin));
        //实验记录，第一次3408，第二次3106，第三次2902
//        //以上是毫秒级别的，下面是纳秒的
//        //开始的时候
//        long end=System.nanoTime();
//        //结束时候
//        System.out.println((System.nanoTime()-begin));
    }

    @Test
    public void paramTest(){
        String uri = "param";
        WebTarget target = getTarget(uri);
        Response response = target.path("queryParam").queryParam("queryParam","xxq").request().get();
        print(response);

        Response response1 = target.path("matrixParam").matrixParam("matrixParam","xxm").request().get();
        print(response1);

        Response response2 = target.path("headerParam").request().header("headerParam","xxh").get();
        print(response2);

        Response response3 = target.path("xxp").request().get();
        print(response3);

        Form form = new Form();
        form.param("formParam","xxf");
        Response response4 = target.path("formParam").request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        print(response4);
    }

    @Test
    public void testProduces(){
        //生成一个target
        WebTarget target = getTarget("produces");
        //请求json
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        assertEquals(response.getStatus(),200);

        response = target.request(MediaType.APPLICATION_XML).get();
        assertEquals(response.getStatus(),200);

        response = target.request(MediaType.APPLICATION_XHTML_XML).get();
        assertEquals(response.getStatus(),406);
    }
}
