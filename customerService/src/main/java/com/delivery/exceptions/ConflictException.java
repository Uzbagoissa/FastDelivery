package com.delivery.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String s) {
        super(s);
    }
}