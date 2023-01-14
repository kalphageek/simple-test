package me.kalpha.processbuilderapi.common;

import lombok.Getter;
import lombok.ToString;

public class Constants {
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
