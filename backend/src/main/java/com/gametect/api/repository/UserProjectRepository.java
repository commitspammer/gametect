package com.gametect.api.repository;

import com.gametect.api.model.UserProject;

import java.util.Optional;

public interface UserProjectRepository {
    UserProject save(UserProject userProject);

    void deleteAll();

    void deleteById(Integer userId, Integer projetoId);

    Optional<UserProject> findById(Integer projetoId, Integer userId);
}
