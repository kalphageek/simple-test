package me.kalpha.processbuilderapi.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    SUCCESS(200, "CMD-200", "성공했습니다"),
    COMMAND_NOT_FOUND(404, "CMD-404", "존재하지 않는 명령어입니다"),
    UNHANDLED_ERROR(500, "CMD-500", "확인되지 않은 에러입니다");

    private int code;
    private String status;
    private String message;

}
