package com.gametect.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;

public class UserProject {

    private Integer projetoId;

    private Integer userId;

    public UserProject() {

    }

    public UserProject(Integer projetoId, Integer userId) {
        this.projetoId = projetoId;
        this.userId = userId;
    }

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
}

