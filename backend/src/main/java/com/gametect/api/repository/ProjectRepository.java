package com.gametect.api.repository;

import com.gametect.api.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    List<Project> findAll();
    List<Project> findByUser(Integer idUser);

    Optional<Project> findById(Integer id);

    Project save(Project user);

    Project update(Project user);

    void deleteAll();

    void deleteById(Integer id);
}
