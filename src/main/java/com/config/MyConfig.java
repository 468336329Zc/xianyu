package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("file:D://campus_shop/pic/");
    }
    @Bean
    public WebMvcConfigurerAdapter WebMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            /**添加拦截器*/
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/admin/**")
                        .addPathPatterns("/user/center")
                        .excludePathPatterns("/admin").
                        excludePathPatterns("/admin/login");
            }

            /**静态资源处理*/
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/pic/**").addResourceLocations("classpath:/static/pic/");
                super.addResourceHandlers(registry);
            }
        };
        return adapter;
    }
}