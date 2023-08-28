package com.gametect.api.model.dto;

import com.gametect.api.enumerations.UserProjectRoleEnum;

import java.util.List;

public class CollaboratorDTO {
    private Integer id;

    private String name;

    private String email;

    private List<UserProjectRoleEnum> roles;

    public CollaboratorDTO(Integer id, String name, String email, List<UserProjectRoleEnum> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

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

    public List<UserProjectRoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<UserProjectRoleEnum> roles) {
        this.roles = roles;
    }
}
