package me.kalpha.processbuilderapi.controller;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.processbuilderapi.common.Constants;
import me.kalpha.processbuilderapi.common.CMDException;
import me.kalpha.processbuilderapi.service.CMDService;
import me.kalpha.processbuilderapi.vo.CMDResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class CMDController {
    private CMDService cMDService;
    public CMDController(CMDService cMDService) {
        this.cMDService = cMDService;
    }

    @GetMapping("/cat/{fileName}")
    public ResponseEntity cat(@PathVariable String fileName) throws IOException, InterruptedException, CMDException {
        Map<String,String> map = cMDService.cat(fileName);
        CMDResponse response = new CMDResponse(map.get(Constants.RESPONSE_MESSAGE));
        return ResponseEntity.of(Optional.of(response));
    }
}
