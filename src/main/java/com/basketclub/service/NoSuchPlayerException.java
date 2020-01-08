package com.basketclub.service;

public class NoSuchPlayerException extends NoSuchEntityException {
    public NoSuchPlayerException() {
    }

    public NoSuchPlayerException(String message) {
        super(message);
    }
}
