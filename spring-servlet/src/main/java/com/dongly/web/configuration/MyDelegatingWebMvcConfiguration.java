package com.dongly.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Created by tiger on 2017/6/17.
 */
@Configuration
public class MyDelegatingWebMvcConfiguration extends DelegatingWebMvcConfiguration {

    /**
     * Set if ";" (semicolon) content should be stripped from the request URI.
     * <p>The default value is {@code true}.
     */
    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        mapping.setRemoveSemicolonContent(false);
        return mapping;
    }
}
