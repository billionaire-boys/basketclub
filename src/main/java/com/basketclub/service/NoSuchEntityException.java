package com.basketclub.service;

public class NoSuchEntityException extends Exception {
    public NoSuchEntityException() {
    }

    public NoSuchEntityException(String message) {
        super(message);
    }
}
