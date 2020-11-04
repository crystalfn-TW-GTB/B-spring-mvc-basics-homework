package com.thoughtworks.capacity.gtb.mvc.exception;

public class UserExitsException extends RuntimeException {
    public UserExitsException(String message) {
        super(message);
    }
}
