package com.aztu.job_application.handler;

import com.aztu.job_application.model.enums.ExceptionMessage;
import com.aztu.job_application.model.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends DefaultErrorAttributes {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Map<String, Object>> handle(ApplicationException ex,
                                                      WebRequest webRequest) {
        return of(ex, webRequest);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handle(NotFoundException ex,
                                                      WebRequest webRequest) {
        return of(ex, webRequest);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handle(UsernameNotFoundException ex,
                                                      WebRequest webRequest) {
        return of(ex, webRequest);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handle(BadCredentialsException ex,
                                                      WebRequest webRequest) {
        return of(ex, webRequest);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handle(DataIntegrityViolationException ex,
                                                      WebRequest webRequest) {
        return of(ex, webRequest);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handle(MethodArgumentNotValidException ex,
                                                      WebRequest webRequest) {
        return of(ex, webRequest);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handle(AccessDeniedException ex,
                                                      WebRequest webRequest) {
        return of(ex, webRequest);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handle(Exception ex,
                                                      WebRequest webRequest) {
        return of(ex, webRequest);
    }

    private Map<String, Object> buildExceptionResponse(String message,
                                                       WebRequest webRequest,
                                                       HttpStatus http) {

        Map<String, Object> errorAttributes = getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        errorAttributes.put("error", message);
        errorAttributes.put("status", http.value());
        errorAttributes.put("timestamp", LocalDateTime.now());
        errorAttributes.put("path", ((ServletRequestAttributes) webRequest).getRequest().getServletPath());

        return errorAttributes;
    }

    private Map<String, Object> buildExceptionResponse(MethodArgumentNotValidException ex,
                                                       WebRequest webRequest) {

        Map<String, Object> invalidFields = new HashMap<>();
        Map<String, Object> errorAttributes = getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());


        ex.getFieldErrors().forEach(fieldError -> invalidFields.put(fieldError.getField(), fieldError.getDefaultMessage()));

        errorAttributes.put("error", invalidFields);
        errorAttributes.put("status", HttpStatus.BAD_REQUEST.value());
        errorAttributes.put("timestamp", LocalDateTime.now());
        errorAttributes.put("path", ((ServletRequestAttributes) webRequest).getRequest().getServletPath());

        return errorAttributes;
    }

    private ResponseEntity<Map<String, Object>> of(ApplicationException ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                buildExceptionResponse(ex.getMessage(),
                        webRequest,
                        ex.getExceptionResponse().getHttpStatus()),
                ex.getExceptionResponse().getHttpStatus());
    }

    private ResponseEntity<Map<String, Object>> of(MethodArgumentNotValidException ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                buildExceptionResponse(ex,
                        webRequest
                ),
                HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, Object>> of(AccessDeniedException ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                buildExceptionResponse(ExceptionMessage.ACCESS_DENIED_EXCEPTION.getMessage(), webRequest, ExceptionMessage.ACCESS_DENIED_EXCEPTION.getHttpStatus()),
                ExceptionMessage.ACCESS_DENIED_EXCEPTION.getHttpStatus());
    }

    private ResponseEntity<Map<String, Object>> of(Exception ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                buildExceptionResponse(ex.getMessage(),
                        webRequest,
                        HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, Object>> of(DataIntegrityViolationException ex, WebRequest webRequest) {
        return new ResponseEntity<>(
                buildExceptionResponse(ExceptionMessage.USERNAME_IS_UNAVAILABLE_EXCEPTION.getMessage(),
                        webRequest,
                        ExceptionMessage.USERNAME_IS_UNAVAILABLE_EXCEPTION.getHttpStatus()),
                ExceptionMessage.USERNAME_IS_UNAVAILABLE_EXCEPTION.getHttpStatus());
    }

    private ResponseEntity<Map<String, Object>> of(BadCredentialsException ex,
                                                   WebRequest webRequest) {
        return new ResponseEntity<>(
                buildExceptionResponse(ExceptionMessage.BAD_CREDENTIALS_EXCEPTION.getMessage(),
                        webRequest,
                        ExceptionMessage.BAD_CREDENTIALS_EXCEPTION.getHttpStatus()),
                ExceptionMessage.BAD_CREDENTIALS_EXCEPTION.getHttpStatus());
    }


    //--------------------------------------------------

//    @ExceptionHandler(ApplicationException.class)
//    public ResponseEntity<Map<String, Object>> handler(ApplicationException exception,
//                                                      WebRequest webRequest) {
//        return of(exception, webRequest);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, Object>> handle(MethodArgumentNotValidException ex,
//                                                      WebRequest request) {
//
//        Map<String, Object> errorAttributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());
//
//        Map<String, Object> invalidFields = new HashMap<>();
//
//        ex.getFieldErrors()
//                .forEach(fieldError -> invalidFields.put(fieldError.getField(),
//                        fieldError.getDefaultMessage()));
//
//        errorAttributes.put("status", HttpStatus.BAD_REQUEST.value());
//        errorAttributes.put("error", invalidFields);
//        errorAttributes.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
//
//        return new ResponseEntity<>(errorAttributes, HttpStatus.BAD_REQUEST);
//    }
//
//
////    @ExceptionHandler(Exception.class)
////    public ResponseEntity<Map<String, Object>> handler(RuntimeException exception,
////                                                       WebRequest webRequest) {
////        return of(exception, webRequest);
////    }
//
//    private Map<String, Object> errorAttributes(HttpStatus httpStatus, String message, WebRequest webRequest) {
//
//        Map<String, Object> errorAttributes = getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
//
//        errorAttributes.put("error", message);
//        errorAttributes.put("status", httpStatus.value());
//        errorAttributes.put("path", ((ServletRequestAttributes)webRequest).getRequest().getServletPath());
//
//        return errorAttributes;
//    }
//
//
//    private ResponseEntity<Map<String, Object>> of (ApplicationException exception, WebRequest webRequest) {
//
//        Map<String, Object> errorAttributes  = errorAttributes(exception.getExceptionResponse().getHttpStatus(), exception.getMessage(), webRequest);
//
//        return new ResponseEntity<>(errorAttributes, exception.getExceptionResponse().getHttpStatus());
//    }
//
//    private ResponseEntity<Map<String, Object>> of (Exception exception, WebRequest webRequest) {
//
//        Map<String, Object> errorAttributes  = errorAttributes(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), webRequest);
//
//        return new ResponseEntity<>(errorAttributes, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
