package com.example.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    private final static long serialVersionUid = 1l;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
