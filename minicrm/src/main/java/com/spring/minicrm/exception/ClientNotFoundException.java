package com.spring.minicrm.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
        super("Client not found");
    }
}
