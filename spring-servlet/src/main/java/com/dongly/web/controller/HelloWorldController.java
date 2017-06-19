package com.dongly.web.controller;

import com.dongly.web.base.WebResultVo;
import com.dongly.web.exception.BaseException;
import com.dongly.web.exception.BusinessException;
import com.dongly.web.exception.EnumStatus;
import org.omg.CORBA.UnknownUserException;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.nio.file.Path;
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

    /**
     * 页面跳转
     * @param model
     * @return
     * @throws BaseException
     */
    @ResponseBody
    @GetMapping(path = "/product/pages")
    public ModelAndView getPages(ModelMap model) throws BaseException {
        ModelAndView view = new ModelAndView();
        view.setViewName("product/product_list");
        model.put("name", "小虎");
        model.put("age", 24);
        model.put("birthday", LocalDate.of(1993, 9, 2).toString());
        view.addObject(model);
        return view;
    }

    /**
     * ajax请求
     * @return
     * @throws BaseException
     */
    @ResponseBody
    @GetMapping(path = "/hello")
    public WebResultVo<Map<String, Object>> hello() throws BaseException, BindException {
        Map<String, Object> result = new HashMap<>();
        result.put("name", "小虎");
        result.put("age", 24);
        result.put("birthday", LocalDate.of(1993, 9, 2).toString());
        return WebResultVo.success(result);
    }

    /**
     * 矩阵变量测试
     * @param q1 param
     * @param ownerId param
     * @param petId param
     * @param q2 param
     */
    // /owners/42;q=11/pets/21;q=22
    @ResponseBody
    @GetMapping(path = "/pass/owners/{ownerId}/pets/{petId}")
    public void findPet(@MatrixVariable(name = "q", pathVar = "ownerId") Integer q1,
                        @PathVariable(name = "ownerId") Integer ownerId,
                        @PathVariable(name = "petId") Integer petId,
                        @MatrixVariable(name = "q", pathVar = "petId") Integer q2) {
        System.out.println("q1 -> " + q1);
        System.out.println("ownerId -> " + ownerId);
        System.out.println("petId -> " + petId);
        System.out.println("q2 -> " + q2);
        // q1 -> 11
        // ownerId -> 42
        // petId -> 21
        // q2 -> 22
    }

    /**
     * 矩阵变量测试
     * @param matrixVars param
     * @param petMatrixVars param
     */
    // GET /owners/42;q=11;r=12/pets/21;q=22;s=23
    @ResponseBody
    @GetMapping(path = "/owners/{ownerId}/pets/{petId}")
    public void findPet(@MatrixVariable Map<String, Object> matrixVars,
                        @MatrixVariable(pathVar="petId") Map<String, Object> petMatrixVars) {
        System.out.println("matrixVars -> " + matrixVars);
        System.out.println("petMatrixVars -> " + petMatrixVars);
        // matrixVars -> {q=11, r=12, s=23}
        // petMatrixVars -> {q=22, s=23}

    }

}
