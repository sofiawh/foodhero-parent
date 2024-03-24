package com.foodhero.user.userservice.exceptions;


public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
