package me.kalpha.processbuilderapi.common;

import me.kalpha.processbuilderapi.vo.CMDResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Map<String,String>> unhandledException(Exception e) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("Unhandled Exception 발생 : {}, {}", e.getCause(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("errorMessage", e.getMessage());

        return new ResponseEntity<>(map, httpHeaders, httpStatus);
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<Map<String,String>> customException(CustomException e) {
        HttpHeaders httpHeaders = new HttpHeaders();

        log.error("Custom Exception 발생 : {}, {}", e.getHttpStatusType(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", e.getHttpStatusType());
        map.put("code", Integer.toString(e.getHttpStatusCode()));
        map.put("errorMessage", e.getMessage());

        return new ResponseEntity<>(map, httpHeaders, e.getHttpStatus());
    }

    @ExceptionHandler(CMDException.class)
    protected ResponseEntity<CMDResponse> handledException(CMDException e) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("Handled Exception 발생 : {}, {}", e.getCause(), e.getErrorCode().getMessage());

        CMDResponse cMDResponse = new CMDResponse();
        cMDResponse.setErrorMessage(e.getMessage());
        cMDResponse.setStatus(e.getErrorCode().getStatus());

        return new ResponseEntity<CMDResponse>(cMDResponse, httpHeaders, httpStatus);
    }
}
