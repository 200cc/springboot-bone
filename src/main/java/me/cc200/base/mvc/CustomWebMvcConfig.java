package me.cc200.base.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class CustomWebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * MessageConverters
     * @param converters
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
    }

    /**
     * 拦截器
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

    /**
     * 静态资源
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }

}
