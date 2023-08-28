package com.gametect.api.controller;

import java.util.List;
import java.util.Optional;

import com.gametect.api.exception.UserNotFoundException;
import com.gametect.api.model.Project;

import com.gametect.api.model.SelectionBox;
import com.gametect.api.model.User;
import com.gametect.api.model.dto.CollaboratorDTO;
import com.gametect.api.model.dto.ProjectToolsDTO;
import com.gametect.api.service.ProjectService;
import com.gametect.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;

	@GetMapping
	public List<Project> getAll() {
		return projectService.getAll();
	}

	@GetMapping("/{id}")
	public Project getById(@PathVariable Integer id) {
		return projectService.getById(id);
	}

	@PostMapping
	public Project create(@RequestBody Project p) {
		return projectService.create(p);
	}

	@PutMapping("/{id}")
	public Project update(@PathVariable Integer id, @RequestBody Project project) {
		project.setId(id);
		return projectService.update(project);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Integer id) {
		projectService.deleteById(id);
	}

	@GetMapping("/{id}/users")
	public List<CollaboratorDTO> getProjects(@PathVariable Integer id) {
		return userService.findByProject(id);
	}

	@GetMapping("/{id}/tools")
	public ResponseEntity<ProjectToolsDTO> getToolsProject(@PathVariable Integer id) {
		Optional<ProjectToolsDTO> projectTools = projectService.toolsProjectbyId(id);
		return projectTools.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
