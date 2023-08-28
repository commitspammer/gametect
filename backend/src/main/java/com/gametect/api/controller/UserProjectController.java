package com.gametect.api.controller;

import com.gametect.api.model.UserProject;
import com.gametect.api.model.UserProjectRole;
import com.gametect.api.service.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-projects")
public class UserProjectController {

    @Autowired
    private UserProjectService userProjectService;

    @GetMapping("/{projetoId}/{userId}")
    public UserProject getById(@PathVariable("projetoId") Integer projetoId, @PathVariable("userId") Integer userId) {
        return userProjectService.findUserProjectById(projetoId, userId);
    }

    @PostMapping
    public UserProjectRole create(@RequestBody UserProjectRole u) {
        return userProjectService.saveAll(u);
    }

    @DeleteMapping("/{projetoId}/{userId}/{description}")
    public void deleteRoleById(@PathVariable("projetoId") Integer projetoId, @PathVariable("userId") Integer userId,  @PathVariable("description") String description) {
        userProjectService.deleteRoleById(projetoId, userId,description);
    }
    @GetMapping("/{projetoId}/{userId}/{description}")
    public UserProjectRole getById(@PathVariable("projetoId") Integer projetoId, @PathVariable("userId") Integer userId,@PathVariable("description") String description) {
        return userProjectService.findRoleById(projetoId, userId, description);
    }

}
