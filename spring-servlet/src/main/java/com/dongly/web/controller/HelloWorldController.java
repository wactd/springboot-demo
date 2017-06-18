package com.dongly.web.controller;

import com.dongly.web.base.WebResultVo;
import com.dongly.web.exception.BaseException;
import com.dongly.web.exception.BusinessException;
import com.dongly.web.exception.EnumStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tiger on 2017/6/17.
 */
@Controller
public class HelloWorldController {


    /**
     * 首页跳转
     * @return 首页
     */
    @GetMapping(path = "/")
    public String index(ModelMap model) {
        model.put("name", "小虎");
        model.put("age", 24);
        model.put("birthday", LocalDate.of(1993, 9, 2).toString());
        return "index";
    }

    @ResponseBody
    @GetMapping(path = "/product/pages")
    public ModelAndView getPages(ModelMap model) throws BaseException {
        ModelAndView view = new ModelAndView();
        // view.setViewName("product/product_list");
        model.put("name", "小虎");
        model.put("age", 24);
        model.put("birthday", LocalDate.of(1993, 9, 2).toString());
        view.addObject(model);
        throw new BaseException(EnumStatus.UNKNOWN_ERROR);
        // return view;
    }

    @ResponseBody
    @GetMapping(path = "/hello")
    public WebResultVo<Map<String, Object>> hello() throws BaseException {
        Map<String, Object> result = new HashMap<>();
        result.put("name", "小虎");
        result.put("age", 24);
        result.put("birthday", LocalDate.of(1993, 9, 2).toString());
        WebResultVo<Map<String, Object>> success = WebResultVo.success(result);
        throw new BusinessException(EnumStatus.UNKNOWN_ERROR);
        // return result;
    }

}
