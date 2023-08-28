package com.gametect.api.repository;

import com.gametect.api.model.User;

import com.gametect.api.model.UserRole;
import com.gametect.api.repository.mappers.UserRoleMappper;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(Integer id);

    User save(User user);

    User update(User user);

    void deleteAll();

    void deleteById(Integer id);

    Optional<User> findByEmail(String email);

    List<UserRole> findByProject(Integer id);
}
