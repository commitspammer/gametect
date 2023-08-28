package com.gametect.api.service;

import com.gametect.api.exception.*;
import com.gametect.api.model.User;
import com.gametect.api.model.UserProject;
import com.gametect.api.model.UserProjectRole;
import com.gametect.api.repository.ProjectRepository;
import com.gametect.api.repository.UserProjectRepository;
import com.gametect.api.repository.UserProjectRoleRepository;
import com.gametect.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProjectService {

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Autowired
    private UserProjectRoleRepository userProjectRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public UserProjectRole saveAll(UserProjectRole role) {
        var optional = userProjectRepository.findById(role.getProjetoId(), role.getUserId());
        if (optional.isPresent()) {
            var optionalRole = userProjectRoleRepository.findById(role.getProjetoId(), role.getUserId(), role.getDescription().getCode());
            if (optionalRole.isPresent()) {
                throw new UserProjectRoleRepeatedException();
            } else {
                validateFields(new UserProject(role.getProjetoId(), role.getUserId()));
                return createRole(role);
            }
        } else {
            create(new UserProject(role.getProjetoId(), role.getUserId()));
            return createRole(role);
        }
    }

    public UserProject create(UserProject u) {
        validateFields(u);
        return userProjectRepository.save(u);
    }

    private void validateFields(UserProject userProject) {
        if (userProject.getUserId() == null) {
            throw new ObrigationsFieldNullException();
        }
        var optionalUser = userRepository.findById(userProject.getUserId());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        if (userProject.getProjetoId() == null) {
            throw new ObrigationsFieldNullException();
        }
        var optionalProject = projectRepository.findById(userProject.getProjetoId());
        if (optionalProject.isEmpty()) {
            throw new ProjectNotFoundException();
        }
    }

    public void findSameProjectUser(Integer projetoId, Integer userId) {
        var optional = userProjectRepository.findById(projetoId, userId);
        if (optional.isPresent()) {
            throw new UserProjectRepeatedException();
        }
    }

    public void deleteById(Integer projetoId, Integer userId) {
        findUserProjectById(projetoId, userId);
        userProjectRepository.deleteById(userId, projetoId);
    }

    public UserProject findUserProjectById(Integer projetoId, Integer userId) {
        var optional = userProjectRepository.findById(projetoId, userId);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new UserProjectNotFoundException();
        }
    }

    public UserProjectRole createRole(UserProjectRole u) {
        validateFieldsRole(u);
        return userProjectRoleRepository.save(u);
    }

    public void deleteRoleById(Integer projetoId, Integer userId, String description) {
        findRoleById(projetoId, userId, description);
        userProjectRoleRepository.deleteById(userId, projetoId, description);
        var optionalUserProject = userProjectRoleRepository.findByProjetoUser(projetoId, userId);
        if(optionalUserProject.isEmpty()){
            userProjectRepository.deleteById(userId, projetoId);
        }
    }

    public UserProjectRole findRoleById(Integer projetoId, Integer userId, String description) {
        var optional = userProjectRoleRepository.findById(projetoId, userId, description);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new UserProjectNotFoundException();
        }
    }

    private void validateFieldsRole(UserProjectRole role) {
        if (role.getDescription() == null) {
            throw new ObrigationsFieldNullException();
        }
    }
}
