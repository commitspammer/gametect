package com.gametect.api.model;

import com.gametect.api.enumerations.UserProjectRoleEnum;

public class UserProjectRole {
    private Integer projetoId;

    private Integer userId;

    private UserProjectRoleEnum description;

    public Integer getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Integer projetoId) {
        this.projetoId = projetoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserProjectRoleEnum getDescription() {
        return description;
    }

    public void setDescription(UserProjectRoleEnum description) {
        this.description = description;
    }
}
