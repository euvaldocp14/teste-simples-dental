package com.test.avaliable.infrastructure.exception;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private String message;
    private long timestamp;
    private int httpStatus;
    private String url;

    @JsonInclude(value = Include.NON_EMPTY)
    private List<ErrorInfo> details;

    @JsonInclude(value = Include.NON_EMPTY)
    private Object body;

    /**
     * For jackson serialization purpose
     */
    ErrorResponse() {

    }

    public ErrorResponse(String message, int httpStatus, String url) {
        this(message, httpStatus, url, null);
    }

    public ErrorResponse(String message, int httpStatus, String url, List<ErrorInfo> details, Object body) {
        this.message = message;
        timestamp = new Date().getTime();
        this.httpStatus = httpStatus;
        this.url = url;
        this.details = details;
        this.body = body;
    }

    public ErrorResponse(String message, int httpStatus, String url, List<ErrorInfo> details) {
        this(message, httpStatus, url, details, null);
    }

    public ErrorResponse(String message, int httpStatus, String url, Object body) {
        this(message, httpStatus, url, null, body);

    }

    @Override
    public String toString() {
        return "ErrorResponse [message=" + message + ", timestamp=" + timestamp + ", httpStatus=" + httpStatus
                + ", url=" + url + ", details=" + details + ", body=" + body + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((body == null) ? 0 : body.hashCode());
        result = prime * result + ((details == null) ? 0 : details.hashCode());
        result = prime * result + httpStatus;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ErrorResponse other = (ErrorResponse) obj;
        if (body == null) {
            if (other.body != null)
                return false;
        } else if (!body.equals(other.body))
            return false;
        if (details == null) {
            if (other.details != null)
                return false;
        } else if (!details.equals(other.details))
            return false;
        if (httpStatus != other.httpStatus)
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (timestamp != other.timestamp)
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

}

