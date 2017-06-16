package com.dongly.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @return
     */
    @GetMapping(path = "/")
    public String index(ModelMap model) {
        model.put("name", "小虎");
        model.put("age", 24);
        model.put("birthday", LocalDate.of(1993, 9, 2).toString());
        return "index";
    }


    @ResponseBody
    @GetMapping(path = "/hello")
    public Map<String, Object> hello() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", "小虎");
        result.put("age", 24);
        result.put("birthday", LocalDate.of(1993, 9, 2).toString());
        return result;
    }


}
