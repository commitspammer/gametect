package com.gametect.api.repository;

import com.gametect.api.model.UserProject;
import com.gametect.api.model.UserProjectRole;

import java.util.Optional;

public interface UserProjectRoleRepository {
    UserProjectRole save(UserProjectRole userProjectRole);

    void deleteAll();

    void deleteById(Integer userId, Integer projetoId, String description);

    Optional<UserProjectRole> findById(Integer projetoId, Integer userId, String description);
    Optional<UserProjectRole> findByProjetoUser(Integer projetoId, Integer userId);
}
