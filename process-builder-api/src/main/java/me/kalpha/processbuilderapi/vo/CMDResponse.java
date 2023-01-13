package me.kalpha.processbuilderapi.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CMDResponse {
    private String status;
    private String responseMessage;
    private String errorMessage;
}
