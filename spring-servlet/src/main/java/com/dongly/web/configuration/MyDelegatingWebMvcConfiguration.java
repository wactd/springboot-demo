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
     * 激活规矩变量(matrix variables)
     * @return
     */
    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        mapping.setRemoveSemicolonContent(false); // 默认为true
        return mapping;
    }

}
