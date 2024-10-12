package edu.hackaton.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

public class ServiceException extends ErrorResponseException {
    private String field;

    public ServiceException(String field, String message) {
        super(HttpStatus.BAD_REQUEST);
        this.field = field;
        setDetail(message);
        setTitle("ServiceException");
        getBody().setProperty("field", field);
    }

    public String getField() {
        return this.field;
    }
}