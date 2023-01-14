package me.kalpha.processbuilderapi.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CMDResponse {
    private String status;
    private String responseMessage;
    private String errorMessage;

    public CMDResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public CMDResponse(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
