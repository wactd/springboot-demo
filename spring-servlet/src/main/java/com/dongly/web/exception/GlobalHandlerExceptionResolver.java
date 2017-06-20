package com.dongly.web.exception;

import com.alibaba.fastjson.JSON;
import com.dongly.web.base.WebResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMethodExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 全局异常解析
 */
public class GlobalHandlerExceptionResolver extends AbstractHandlerMethodExceptionResolver {


    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);

    /**
     * Actually resolve the given exception that got thrown during on handler execution,
     * returning a ModelAndView that represents a specific error page if appropriate.
     * <p>May be overridden in subclasses, in order to apply specific exception checks.
     * Note that this template method will be invoked <i>after</i> checking whether this
     * resolved applies ("mappedHandlers" etc), so an implementation may simply proceed
     * with its actual exception handling.
     *
     * @param request       current HTTP request
     * @param response      current HTTP response
     * @param handlerMethod the executed handler method, or {@code null} if none chosen at the time
     *                      of the exception (for example, if multipart resolution failed)
     * @param ex            the exception that got thrown during handler execution
     * @return a corresponding ModelAndView to forward to, or {@code null} for default processing
     */
    @Override
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response,
                                                           HandlerMethod handlerMethod, Exception ex) {

        if (Objects.isNull(handlerMethod)) {
            // TODO 待定,以后处理
            LOGGER.info("\n===>>>{} 解析失败", handlerMethod.getClass());
            return null;
        }

        Method method = handlerMethod.getMethod();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("\n获取目标方法 method===>>>{}", method);
        }

        // 获取返回类型
        Class<?> returnType = method.getReturnType();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("\n获取返回类型 returnType--->{}", returnType);
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
    }
}
