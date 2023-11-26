package com.test.avaliable.infrastructure.exception;

import com.test.avaliable.infrastructure.exception.dto.ErrorDetails;
import com.test.avaliable.infrastructure.exception.dto.ErrorFields;
import com.test.avaliable.infrastructure.exception.dto.Errors;
import com.test.avaliable.infrastructure.exception.dto.Meta;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@RestControllerAdvice
public class HandleError {

    private final MessageSource messageSource;

    @Autowired
    public HandleError(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInput(HttpMessageNotReadableException e, HttpServletRequest req) {
        return new ResponseEntity<>(new ErrorResponse(e.getCause().getCause().getMessage(),
                HttpStatus.BAD_REQUEST.value(), req.getRequestURL().toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInput(PropertyReferenceException e, HttpServletRequest req) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(),
                req.getRequestURL().toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInput(IllegalArgumentException e, HttpServletRequest req) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(),
                req.getRequestURL().toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorFields>> handle(MethodArgumentNotValidException exception){
        System.out.println("MethodArgumentNotValidException exception is called");
        List<ErrorFields> dto = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach( e -> {
            String message = messageSource.getMessage(e , LocaleContextHolder.getLocale());
            System.out.println("Error message: "+message);
            ErrorFields error = new ErrorFields(e.getField(), message);
            dto.add(error);
        });
        System.out.println("DTO List size: "+dto.size());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorResponse> handle(BusinessException ex, HttpServletRequest req) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                req.getRequestURL().toString(), ex.getDetails()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handler(ResourceNotFoundException ex, HttpServletRequest req){
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), req.getRequestURL().toString()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        final Class<?> requiredType = ex.getRequiredType();
        String error = ex.getName() + " should be of type " + (nonNull(requiredType) ? requiredType.getName() : "");

        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(buildErrors(status.value(),
                ex.getLocalizedMessage(), error, request), new HttpHeaders(), status);
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<ErrorResponse> handle(SQLException sql, HttpServletRequest req) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        StringBuilder builderMsg = new StringBuilder();
        builderMsg.append(sql.getMessage());
        builderMsg.append(" SQL error code: ").append(sql.getErrorCode());
        if (sql.getNextException() != null) {
            builderMsg.append(" NextEx: " + sql.getNextException().getErrorCode() + " " + sql.getNextException().getSQLState());
        }

        return new ResponseEntity<>(
                new ErrorResponse(
                        builderMsg.toString(),
                        httpStatus.value(),
                        req.getRequestURL().toString()
                ),
                httpStatus
        );
    }

    private ErrorResponse buildErrors(Integer statusCode, String exceptionMessage, String error
            , WebRequest request) {
        var errors = Errors.builder()
                .errors(Collections.singletonList(
                        ErrorDetails.builder()
                                .code(String.valueOf(statusCode))
                                .title(exceptionMessage)
                                .detail(error)
                                .build()))
                .meta(Meta.builder()
                        .totalRecords(1)
                        .totalPages(1)
                        .build())
                .build();

        return new ErrorResponse(exceptionMessage, statusCode, getURL(request), null, errors);
    }

    private String getURL(WebRequest request) {
        return Optional.ofNullable(request)
                .map(e -> ((ServletWebRequest) e).getRequest())
                .filter(e -> e != null)
                .map(e -> e.getRequestURL())
                .filter(e -> e != null)
                .map(e -> e.toString())
                .orElse("URL não definida");
    }
}
