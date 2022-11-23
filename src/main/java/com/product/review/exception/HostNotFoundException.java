package com.product.review.exception;

public class HostNotFoundException extends RuntimeException{

    public HostNotFoundException(String message) {
        super(message);
    }
}
