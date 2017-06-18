package com.dongly.web.exception;

import com.alibaba.fastjson.JSON;
import com.dongly.web.base.WebResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 全局异常解析
 */
@Component
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {


    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {

        if (HandlerMethod.class.isInstance(handler)) {

            if (LOGGER.isInfoEnabled()) {
                LOGGER.error("方法执行失败", ex);
            }

            HandlerMethod handlerMethod = HandlerMethod.class.cast(handler);

            Method method = handlerMethod.getMethod();

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("获取目标方法 method--->{}", method);
            }

            // 获取返回类型
            Class<?> returnType = method.getReturnType();

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("获取返回类型 returnType--->{}", returnType);
            }

            // 判断返回类型是否为WebResultVo
            if (WebResultVo.class.isAssignableFrom(returnType)) {

                // 判断是否为自定义异常
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                try (PrintWriter printWriter = response.getWriter()) {
                    if (BusinessException.class.isInstance(ex)) {
                        BusinessException businessException = BusinessException.class.cast(ex);

                        printWriter.write(JSON.toJSONString(WebResultVo.error(businessException.getCode(),
                                businessException.getMessage())));

                    } else if (BaseException.class.isInstance(ex)) {
                        BaseException baseException = BaseException.class.cast(ex);
                        printWriter.write(JSON.toJSONString(WebResultVo.error(baseException.getCode(),
                                baseException.getMessage())));
                    } else {
                        printWriter.write(JSON.toJSONString(WebResultVo.error()));
                    }
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    // 错误页面跳转
                    return new ModelAndView("error");
                }
            } else {
                // 错误页面跳转
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("title", "服务器内部错误");
                modelAndView.addObject("url", request.getRequestURL());
                modelAndView.addObject("error", ex);
                modelAndView.setViewName("error/500");
                return modelAndView;
            }
        } else {
            // 错误页面跳转
            return null;
        }
    }


}
