package me.kalpha.trapi.icems.controller;

import me.kalpha.trapi.common.BaseControllerTest;
import me.kalpha.trapi.icems.entity.Eqp1Tr;
import me.kalpha.trapi.icems.entity.Eqp1TrDet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class Eqp1TrControllerTest extends BaseControllerTest {

    @Test
    public void saveTr() throws Exception {
        Eqp1Tr eqp1Tr = generateEqp1Tr("lot2");

        mockMvc.perform(post("/icems/tr")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(eqp1Tr)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        ;
    }

    private Eqp1Tr generateEqp1Tr(String trName) {
        Eqp1Tr eqp1Tr = Eqp1Tr.builder()
                .name(trName).value(123454l).eventTime(LocalDateTime.now())
                .build();
        Eqp1TrDet eqp1TrDet1 = Eqp1TrDet.builder()
                .eqp1Tr(eqp1Tr).col1("col1").col2(837466l)
                .build();
        Eqp1TrDet eqp1TrDet2 = Eqp1TrDet.builder()
                .eqp1Tr(eqp1Tr).col1("col2").col2(44l)
                .build();
        return eqp1Tr;
    }
}