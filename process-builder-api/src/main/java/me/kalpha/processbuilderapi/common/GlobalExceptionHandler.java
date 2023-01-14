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

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CMDResponse> unhandledException(Exception e) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

//        Map<String, String> map = new HashMap<>();
//        map.put(Constants.ERROR_TYPE, httpStatus.getReasonPhrase());
//        map.put(Constants.ERROR_CODE, "400");
//        map.put(Constants.ERROR_MESSAGE, e.getMessage());
        CMDResponse response =
                new CMDResponse(httpStatus.getReasonPhrase(), e.getMessage());
        log.error("Unhandled Exception 발생 : {}, {}", httpStatus.getReasonPhrase(), e.getMessage());

        return new ResponseEntity<>(response, httpHeaders, httpStatus);
    }

    @ExceptionHandler(CMDException.class)
    protected ResponseEntity<CMDResponse> customException(CMDException e) {
        HttpHeaders httpHeaders = new HttpHeaders();

//        Map<String, String> map = new HashMap<>();
//        map.put(Constants.ERROR_TYPE, e.getHttpStatusType());
//        map.put(Constants.ERROR_CODE, Integer.toString(e.getHttpStatusCode()));
//        map.put(Constants.ERROR_MESSAGE, e.getMessage());
        CMDResponse response =
                new CMDResponse(e.getHttpStatusType(), e.getMessage());
        log.error("Handled Exception 발생 : {}, {}", e.getHttpStatusType(), e.getMessage());

        return new ResponseEntity<>(response, httpHeaders, e.getHttpStatus());
    }
}
