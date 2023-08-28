package com.gametect.api.exception;

public class UserProjectNotFoundException extends RuntimeException{
    public UserProjectNotFoundException() {
        super("User project not found");
    }
}
