package com.gametect.api.exception;

public class InUseEmailException extends RuntimeException{
    public InUseEmailException() {
        super("E-mail informed is already in use");
    }
}
