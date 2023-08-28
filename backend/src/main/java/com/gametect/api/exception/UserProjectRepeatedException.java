package com.gametect.api.exception;

public class UserProjectRepeatedException extends RuntimeException{
    public UserProjectRepeatedException() {
        super("The user is already save in this project");
    }

}
