package com.gametect.api.exception;

public class ObrigationsFieldNullException extends RuntimeException{
    public ObrigationsFieldNullException() {
        super("Obrigations fields can not be null");
    }
}
