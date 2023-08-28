package com.gametect.api.controller;

import java.util.List;

import com.gametect.api.model.Project;
import com.gametect.api.model.User;
import com.gametect.api.model.ProjectDTO;
import com.gametect.api.exception.UserNotFoundException;

import com.gametect.api.service.ProjectService;
import com.gametect.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProjectService projectService;

	@GetMapping
	public List<User> getAll() {
		return userService.getAll();
	}

	@GetMapping("/{id}")
	public User getById(@PathVariable Integer id) {
		return userService.getById(id);
	}

	@PostMapping
	public User create(@RequestBody User u) {
		return userService.create(u);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Integer id) {
		userService.deleteById(id);
	}

	@PutMapping("/{id}")
	public User update(@PathVariable Integer id, @RequestBody User user) {
		user.setId(id);
		return userService.update(user);
	}

	@GetMapping("/{id}/projects")
	public List<Project> getProjects(@PathVariable Integer id) {
		User user = userService.getOne(u -> u.getId() == id).orElseThrow(UserNotFoundException::new);
		return projectService.findByUser(id);
	}

}
