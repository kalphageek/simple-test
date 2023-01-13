package me.kalpha.processbuilderapi.common;

import me.kalpha.processbuilderapi.vo.CMDResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(CMDException.class)
    protected ResponseEntity<CMDResponse> handledException(CMDException e) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("Handled Exception 발생 : {}", e.getErrorCode().getMessage());

        CMDResponse cMDResponse = new CMDResponse();
        cMDResponse.setErrorMessage(e.getMessage());
        cMDResponse.setStatus(e.getErrorCode().getStatus());

        return new ResponseEntity<CMDResponse>(cMDResponse, httpHeaders, httpStatus);
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CMDResponse> unhandledException(Exception e) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("Unhandled Exception 발생 : {}", e.getMessage());

        CMDResponse cMDResponse = new CMDResponse();
        cMDResponse.setErrorMessage(e.getMessage());
        cMDResponse.setStatus("500");

        return new ResponseEntity<CMDResponse>(cMDResponse, httpHeaders, httpStatus);
    }
}
