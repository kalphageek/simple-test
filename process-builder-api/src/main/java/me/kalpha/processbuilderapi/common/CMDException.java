package me.kalpha.processbuilderapi.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CMDException extends RuntimeException {
    private final ErrorCode errorCode;
    public CMDException (String message) {
        super(message);
        this.errorCode = ErrorCode.UNHANDLED_ERROR;
    }
}
