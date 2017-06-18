package com.dongly.web.configuration;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 保留Spring Boot MVC特性, 自定义其他MVC配置（拦截器，格式化处理器，视图控制器等）
 * 加@EnableWebMvc 代表全面控制Spring MVC
 */
@Configurable
public class MyWebMvcConfigurerAdapte extends WebMvcConfigurerAdapter {

    /**
     * 使用FastJson进行转换
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter4 fastConverter = new FastJsonHttpMessageConverter4();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setFeatures(Feature.AllowArbitraryCommas,
                Feature.AllowUnQuotedFieldNames,
                Feature.DisableCircularReferenceDetect);

        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }


    /**
     * 静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("static/**") // 请求URL
                .addResourceLocations("classpath:static/"); // 本地路径
    }
}
