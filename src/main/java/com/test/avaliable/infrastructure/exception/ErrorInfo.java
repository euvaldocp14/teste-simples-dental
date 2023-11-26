package com.test.avaliable.infrastructure.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class ErrorInfo {
    private String message;
    private List<ErrorInfo> details;

    /**
     * For jackson serialization purpose
     */
    ErrorInfo() {

    }

    public ErrorInfo(String message) {
        this.message = message;
        this.details = null;
    }

    public ErrorInfo(String message, List<ErrorInfo> details) {
        this.message = message;
        this.details = details;
    }

    public ErrorInfo(String message, ErrorInfo... details) {
        this.message = message;
        if (details != null) {
            this.details = Arrays.asList(details);
        } else {
            this.details = null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ErrorInfo errorInfo = (ErrorInfo) o;
        return Objects.equals(message, errorInfo.message) &&
                Objects.equals(details, errorInfo.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, details);
    }

    @Override
    public String toString() {
        return "ErrorInfo [message=" + message + ", details=" + details + "]";
    }
}
