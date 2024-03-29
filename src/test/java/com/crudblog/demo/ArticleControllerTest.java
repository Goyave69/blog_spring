package com.crudblog.demo;

import com.crudblog.demo.auth.domain.entity.User;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties"
)
public class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    public String generateAuthToken() throws Exception {
        JSONObject jo = new JSONObject();
        jo.put("username", "johndoe");
        jo.put("password", "johndoe");

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .content(jo.toString())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        MvcResult result = resultActions.andReturn();

        return result.getResponse().getContentAsString();
    }

    @Test
    public void TestReadAll() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/articles")
                                .header("Authorization", "Bearer " + generateAuthToken())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }
}
