package com.atguigu.springbootweb.config;

import com.atguigu.springbootweb.filter.MyFilter;
import com.atguigu.springbootweb.listener.MyListener;
import com.atguigu.springbootweb.servlet.MyServlet;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.logging.Filter;

@Configuration
public class MyServerConfig {

    //注册三大主件
    //servlet
    @Bean
    public ServletRegistrationBean myServlet(){
         ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(), "/myServlet");
         return registrationBean;
    }
    //filter
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean  registrationBean  =new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return registrationBean;
    }
    //listener
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean=new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }



    //自定义tomcat端口
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(){
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setPort(8083);
        return factory;
    }
}
