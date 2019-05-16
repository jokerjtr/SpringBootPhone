package com.jtr.phoneshop.MyConfig;

import com.jtr.phoneshop.MyInterface.adminInterfacter;
import com.jtr.phoneshop.MyInterface.myInterfacter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class myconfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index.html");
        registry.addViewController("/index").setViewName("/index.html");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new myInterfacter()).addPathPatterns("/tovip","/toshopcard","/addshopcard","/toorder");
        registry.addInterceptor(new adminInterfacter()).addPathPatterns("/admin/**").excludePathPatterns("/admin/tologin").excludePathPatterns("/admin/toindex");
    }
}
