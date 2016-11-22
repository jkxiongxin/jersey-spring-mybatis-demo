package com.xx.jersey.demo.main;

import com.alibaba.druid.support.http.StatViewServlet;
import com.xx.jersey.demo.resource.StatServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.Iterator;

/**
 * Created by hzxiongxin on 2016/11/16.
 */
public class Main {

    public static void main(String[] args){

        ApplicationConfig applicationConfig = new ApplicationConfig();
        ServletContainer servletContainer = new ServletContainer(applicationConfig);
        ServletHolder jerseyServlet = new ServletHolder(servletContainer);//使用刚创建好的配置文件创建一个jersey servlet(Servlet持有者)
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(jerseyServlet,"/rest/*");
        context.addServlet(StatServlet.class,"/druid/*");
//        context.addFilter(CharacterEncodingFilter.class,"/*",EnumSet.allOf(DispatcherType.class));
        context.addEventListener(new ContextLoaderListener());
        context.addEventListener(new RequestContextListener());
        context.setInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
        context.setInitParameter("contextConfigLocation",SpringJavaConfiguration.class.getName());

        //配置端口号
        int port = 8080;//默认端口为8080
        if(args.length == 1){
            port = Integer.parseInt(args[0]);
        }


        Server server = new Server(port);
        server.setHandler(context);
        server.setStopTimeout(5000);
        try{
            server.start();
            server.join();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
