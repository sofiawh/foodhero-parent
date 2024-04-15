package com.foodhero.comment.commentservice.exceptions;


public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
