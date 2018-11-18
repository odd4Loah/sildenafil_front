package com.jnshu.sildenafil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @ProjectName: sildenafil
 * @Package: com.jnshu.sildenafil
 * @ClassName: MvcTest
 * @Description: java类作用描述
 * @Author: Taimur
 * @CreateDate: 2018/11/12 14:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MvcTest {
    private MockMvc mockMvc;
    @Autowired
    private org.springframework.web.context.WebApplicationContext webApplicationContext;
    @Before // 在测试开始前初始化工作
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void testQ1()throws Exception{
     mockMvc.perform(MockMvcRequestBuilders.get("/a/u/admin/reviews/type")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .param("page", "1")
                    .param("size", "2")
                    .param("type","1")
                    .param("typeId","1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void Q2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/a/u/front/forum/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("page", "1")
                .param("size", "2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
