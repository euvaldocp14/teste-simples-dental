package com.test.avaliable.infrastructure.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class BusinessException extends RuntimeException {

    private List<ErrorInfo> details;
    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, List<ErrorInfo> details){
        this(message);
        this.details = details;
    }

    public BusinessException(String message, ErrorInfo... details){
        this(message);

        if(details != null){
            this.details = Arrays.asList(details);
        }

    }


}
