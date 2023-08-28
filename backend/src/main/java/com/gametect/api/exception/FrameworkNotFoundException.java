package com.gametect.api.exception;

public class FrameworkNotFoundException extends RuntimeException{
    public FrameworkNotFoundException() {
        super("Framework not found");
    }

}
