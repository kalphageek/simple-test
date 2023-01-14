package me.kalpha.processbuilderapi.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CMDControllerTest {

    @Autowired
    MockMvc mockMvc;
    String fileName = "2022-12-18-utility-tools.md";

    @DisplayName("cat명령어 테스")
    @Test
    public void catTest() throws Exception {
        mockMvc.perform(get("/api/cat/{fileName}", fileName))
                .andDo(print())
                .andExpect(status().isOk())
                ;
    }

    @DisplayName("파일 없음 에러 테스트")
    @Test
    public void badCatTest() throws Exception {
        mockMvc.perform(get("/api/cat/{fileName}", "aaa"))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }
}