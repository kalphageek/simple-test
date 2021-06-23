package me.kalpha.neo4jdml.controller;

import me.kalpha.neo4jdml.common.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class QueryControllerTest extends BaseControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void getQueriesByTable() throws Exception {
        String name = "Table1";
        mockMvc.perform(get("/queries/table/{name}", name))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void getAllQueries() throws Exception {
        mockMvc.perform(get("/queries"))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

}