package com.blog.config;

import com.blog.commons.MessageConstant;
import com.blog.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ProjectName: dal-blog
 * @ClassName: BlogLoginConfig
 * @Author: jian
 * @Description:
 * @Date: 2021/6/7 16:40
 * @Version: 1.0
 */
@Configuration
public class BlogLoginConfig implements WebMvcConfigurer {
    //    @Autowired
//    private LoginInterceptor loginInterceptor;

    @Value("${blog.upload.dir}")
    private String filePath;

    //无法注入拦截器
    @Bean
    public LoginInterceptor loginInterceptor() {
        System.out.println("注册一下===============");
        return new LoginInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，拦截以/admin为前缀的url路径
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/login").excludePathPatterns("/admin/dist/**").excludePathPatterns("/admin/plugins/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + filePath);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");//配置浏览器直接访问static下的静态资源
         }

}
