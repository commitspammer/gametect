package com.gametect.api.model;

import com.gametect.api.enumerations.UserProjectRoleEnum;

public class UserRole {
    private Integer id;

    private String name;

    private String email;

    private UserProjectRoleEnum role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserProjectRoleEnum getRole() {
        return role;
    }

    public void setRole(UserProjectRoleEnum role) {
        this.role = role;
    }
}
