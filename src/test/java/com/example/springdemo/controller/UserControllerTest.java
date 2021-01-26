package com.example.springdemo.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.example.springdemo.form.UserForm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }

    @Test
    public void index() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:9091/user")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .characterEncoding("utf-8")
                .param("pageNum", "10")
                .param("pageSize", "12")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        JSON result = JSONUtil.parse(content);
        System.out.println(result.getByPath("code"));
        JSON data = (JSON) result.getByPath("data");
        System.out.println(data.getByPath("number"));
        // 断言
        Assert.assertEquals(9, data.getByPath("number"));
    }

    @Test
    public void save() throws Exception {
        UserForm userForm = new UserForm();
        userForm.setAge(28);
        userForm.setName("陈明test");
        String userFormStr = JSONUtil.toJsonStr(userForm);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:9091/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userFormStr)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }
}