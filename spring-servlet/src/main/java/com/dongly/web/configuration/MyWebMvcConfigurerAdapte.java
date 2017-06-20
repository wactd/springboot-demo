package com.dongly.web.configuration;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.dongly.web.exception.GlobalHandlerExceptionResolver;
import com.dongly.web.interceptor.MyCustomInterceptor;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines callback methods to customize the Java-based configuration for
 * Spring MVC enabled via {@code @EnableWebMvc}.
 * <p>
 * <p>{@code @EnableWebMvc}-annotated configuration classes may implement
 * this interface to be called back and given a chance to customize the
 * default configuration. Consider extending {@link WebMvcConfigurerAdapter},
 * which provides a stub implementation of all interface methods.
 *
 * @since 3.1
 */
@Configuration
public class MyWebMvcConfigurerAdapte extends WebMvcConfigurerAdapter {

    /**
     * Add Spring MVC lifecycle interceptors for pre- and post-processing of
     * controller method invocations. Interceptors can be registered to apply
     * to all requests or be limited to a subset of URL patterns.
     * <p><strong>Note</strong> that interceptors registered here only apply to
     * controllers and not to resource handler requests. To intercept requests for
     * static resources either declare a
     * {@link org.springframework.web.servlet.handler.MappedInterceptor MappedInterceptor}
     * bean or switch to advanced configuration mode by extending
     * {@link org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport}
     * and then override {@code resourceHandlerMapping}.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyCustomInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/error/**", "/index.html");
        // super.addInterceptors(registry);
    }

    /**
     * Add handlers to serve static resources such as images, js, and, css
     * files from specific locations under web application root, the classpath,
     * and others.
     * e.g
     * {@link WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#addResourceHandlers(ResourceHandlerRegistry)}
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 防止html页面不能访问静态资源
        registry.addResourceHandler("/static/**") // 请求URL
                .addResourceLocations("classpath:static/"); // 本地路径
    }

    /**
     * Configure simple automated controllers pre-configured with the response
     * status code and/or a view to render the response body. This is useful in
     * cases where there is no need for custom controller logic -- e.g. render a
     * home page, perform simple site URL redirects, return a 404 status with
     * HTML content, a 204 with no content, and more.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        // registry.addViewController("/index.html").setViewName("/index");

        // Map a view controller to the given URL path (or pattern) in order to redirect  to another URL
        registry.addRedirectViewController("/index.html", "/");
        registry.addRedirectViewController("/index", "/");
    }

    /**
     * Configure exception resolvers.
     * <p>The given list starts out empty. If it is left empty, the framework
     * configures a default set of resolvers, see
     * {@link WebMvcConfigurationSupport#addDefaultHandlerExceptionResolvers(List)}.
     * Or if any exception resolvers are added to the list, then the application
     * effectively takes over and must provide, fully initialized, exception
     * resolvers.
     * <p>Alternatively you can use
     * {@link #extendHandlerExceptionResolvers(List)} which allows you to extend
     * or modify the list of exception resolvers configured by default.
     * @param exceptionResolvers initially an empty list
     * @see #extendHandlerExceptionResolvers(List)
     * @see WebMvcConfigurationSupport#addDefaultHandlerExceptionResolvers(List)
     */
    // @Override
    // public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
    //     super.configureHandlerExceptionResolvers(exceptionResolvers);
    //     exceptionResolvers.add(new GlobalHandlerExceptionResolver());
    // }

    /**
     * Extending or modify the list of exception resolvers configured by default.
     * （扩展或修改默认配置的异常解析器列表。）
     * This can be useful for inserting a custom exception resolver without interfering with default ones.
     * （这可以用于插入自定义异常解析器，而不会干扰默认异常解析器。）
     *
     * @param exceptionResolvers the list of configured resolvers to extend （扩展已配置的解析器列表）
     * @see WebMvcConfigurationSupport#addDefaultHandlerExceptionResolvers(List)
     * @since 4.3
     */
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new GlobalHandlerExceptionResolver());
    }


    /**
     * Configure the {@link HttpMessageConverter}s to use for reading or writing
     * to the body of the request or response.
     * If no converters are added, a default list of converters is registered.
     * (如果没有加入转换器，则会注册默认的转换器列表。)
     * <p><strong>Note</strong> (注意)
     * that adding converters to the list, turns off default converter registration.
     * （将转换器添加到列表中，关闭默认转换器注册）
     * To simply add a converter without impacting
     * default registration, consider using the method
     * {@link #extendMessageConverters(java.util.List)} instead.
     *
     * @param converters initially an empty list of converters
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

}
