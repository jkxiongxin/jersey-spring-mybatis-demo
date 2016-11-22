package com.xx.jersey.demo.main;

import com.xx.jersey.demo.aop.CompressionWriterInterceptor;
import com.xx.jersey.demo.filter.RequestFilter;
import com.xx.jersey.demo.filter.RequestFilterS;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hzxiongxin on 2016/11/16.
 */
public class ApplicationConfig extends ResourceConfig{

    public ApplicationConfig(){
        packages("com.xx.jersey.demo.resource","com.xx.jersey.demo.http","com.xx.jersey.demo.aop");//配置资源包路径
        register(JacksonFeature.class);//开启json支持
        register(MultiPartFeature.class);
        register(CompressionWriterInterceptor.class);
        property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE,true);//关闭服务端的service扫描功能
//        register(RequestFilter.class,1);
//        register(RequestFilterS.class,2);
    }

}
