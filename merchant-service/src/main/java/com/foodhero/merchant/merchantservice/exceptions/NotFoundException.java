package com.foodhero.merchant.merchantservice.exceptions;


public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
