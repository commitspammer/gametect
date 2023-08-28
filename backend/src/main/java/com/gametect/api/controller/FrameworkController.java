package com.gametect.api.controller;

import java.util.List;

import com.gametect.api.service.FrameworkService;
import com.gametect.api.model.dto.FrameworkDTO;

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
@RequestMapping("/frameworks")
public class FrameworkController {

	@Autowired
	private FrameworkService frameworkService;

	@GetMapping
	public List<FrameworkDTO> getAll() {
		return frameworkService.listAll();
	}

	@GetMapping("/{id}")
	public FrameworkDTO getById(@PathVariable Integer id) {
		return frameworkService.findById(id);
	}

	@PostMapping
	public FrameworkDTO createNew(@RequestBody FrameworkDTO f) {
		return frameworkService.create(f);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Integer id) {
		frameworkService.delete(id);
	}

}
