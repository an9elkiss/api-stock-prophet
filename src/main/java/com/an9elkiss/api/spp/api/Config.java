package com.an9elkiss.api.spp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableAspectJAutoProxy
@Configuration
public class Config implements WebMvcConfigurer {

    @Autowired
    private RegionPrivilegeInterceptor regionPrivilegeInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(regionPrivilegeInterceptor);
    }

}
