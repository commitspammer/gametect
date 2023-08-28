package com.gametect.api.exception;

public class UserProjectRoleRepeatedException extends RuntimeException {
    public UserProjectRoleRepeatedException() {
        super("The user is already save in this project with this role");
    }

}
