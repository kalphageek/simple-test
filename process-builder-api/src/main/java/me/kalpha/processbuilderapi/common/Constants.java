package me.kalpha.processbuilderapi.common;

import lombok.Getter;
import lombok.ToString;

public class Constants {
    public static String RESPONSE_MESSAGE = "responseMessage";
    public static String ERROR_MESSAGE = "errorMessage";
    public static String ERROR_CODE = "errorCode";
    public static String ERROR_TYPE = "errorType";

    public enum ExceptionClass {
        HANDLED("Handled"), UNHANDLED("Unhandled");

        private String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }
        public String getExceptionClass() {
            return exceptionClass;
        }

        @Override
        public String toString() {
            return getExceptionClass() + " Exception.";
        }
    }
}
