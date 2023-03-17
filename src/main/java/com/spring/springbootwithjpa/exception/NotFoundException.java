package com.spring.springbootwithjpa.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Data Not Found!");
    }

    public NotFoundException(String message) {
        super(message);
    }

}
