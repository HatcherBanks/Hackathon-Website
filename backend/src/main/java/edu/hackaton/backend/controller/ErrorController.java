package edu.hackaton.backend.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler  {
    @ExceptionHandler
    ErrorResponse handleConstraintViolationException(ConstraintViolationException cve) {
        List<String> violations = cve.getConstraintViolations().stream().map(cv -> cv.getPropertyPath() + ": " + cv.getMessage()).toList();

        return ErrorResponse.builder(cve, HttpStatus.BAD_REQUEST, cve.getMessage()).title("Constraint Violation").property("violations", violations).build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var result = ex.getBindingResult();
        ex.getBody().setDetail(ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList().toString());
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail body = createProblemDetail(ex, status, ex.getMessage(), null, null, request);
		return handleExceptionInternal(ex, body, headers, status, request);
    }
    
}
