package com.gametect.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.Optional;

import com.gametect.api.enumerations.UserProjectRoleEnum;
import com.gametect.api.exception.InUseEmailException;
import com.gametect.api.exception.ObrigationsFieldNullException;
import com.gametect.api.exception.UserNotFoundException;
import com.gametect.api.model.User;
import com.gametect.api.model.dto.CollaboratorDTO;
import com.gametect.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<User> getOnly(Predicate<User> condition) {
        return userRepository.findAll().stream().filter(condition).toList();
    }

    public Optional<User> getOne(Predicate<User> condition) {
        return userRepository.findAll().stream().filter(condition).findFirst();
    }

    public User getById(Integer id) {
        var optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new UserNotFoundException();
        }
    }

    public User create(User u) {
        validateFields(u);
        return userRepository.save(u);
    }

    public void deleteById(Integer id) {
        getById(id);
        userRepository.deleteById(id);
    }

    public User update(User user) {
        getById(user.getId());
        validateFields(user);
        return userRepository.update(user);
    }

    public void deleteOnly(Predicate<User> condition) {
//		userRepository.deleteAll(userRepository.findAll().stream().filter(condition).toList());
    }

    private void validateFields(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new ObrigationsFieldNullException();
        }
        if (user.getId() == null && (user.getPassword() == null || user.getPassword().isEmpty())) {
            throw new ObrigationsFieldNullException();
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ObrigationsFieldNullException();
        }
        var optional = userRepository.findByEmail(user.getEmail());
        if (optional.isPresent()) {
            if (user.getId() == null || optional.get().getId() != user.getId()) {
                throw new InUseEmailException();
            }
        }
    }

    public List<CollaboratorDTO> findByProject(Integer idProject) {
        List<CollaboratorDTO> collabs = new ArrayList<>();
        var userRoleList = userRepository.findByProject(idProject);
        for (var userRole :
                userRoleList) {
            if (collabs.stream().filter(c->c.getId() == userRole.getId()).toList().isEmpty()) {
                var users = userRoleList.stream().filter(u -> u.getId() == userRole.getId()).toList();
                var roles = new ArrayList<UserProjectRoleEnum>();
                users.forEach(u -> roles.add(u.getRole()));
                if (!roles.isEmpty()) {
                    collabs.add(new CollaboratorDTO(userRole.getId(), userRole.getName(), userRole.getEmail(), roles));
                }
            }

        }
        return collabs;
    }

}
