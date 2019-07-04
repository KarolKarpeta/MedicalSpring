package com.medbis.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class UserInterceptorConfig implements WebMvcConfigurer {

    private final UserInterceptor userInterceptor;

    public UserInterceptorConfig(UserInterceptor userInterceptor) {
        this.userInterceptor = userInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(userInterceptor).addPathPatterns("/patients");
    }
}
