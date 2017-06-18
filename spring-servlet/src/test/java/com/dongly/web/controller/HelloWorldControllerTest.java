package com.dongly.web.controller;

import com.dongly.SpringServletApplicationTests;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by tiger on 2017/6/17.
 */
public class HelloWorldControllerTest extends SpringServletApplicationTests {

    @Test
    public void index() throws Exception {
        ResultActions actions = super.mockMvc.perform(MockMvcRequestBuilders.get("/hello"));
        actions = actions.andDo(MockMvcResultHandlers.print());
        actions = actions.andExpect(MockMvcResultMatchers.status().isOk());
        actions.andReturn();
    }
    @Test
    public void getPages() throws Exception {
    }

    @Test
    public void hello() throws Exception {
    }

}