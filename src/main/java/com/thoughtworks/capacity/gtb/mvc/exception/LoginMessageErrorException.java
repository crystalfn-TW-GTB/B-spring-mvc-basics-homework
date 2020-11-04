package com.thoughtworks.capacity.gtb.mvc.exception;

public class LoginMessageErrorException extends RuntimeException {
    public LoginMessageErrorException(String message) {
        super(message);
    }
}
