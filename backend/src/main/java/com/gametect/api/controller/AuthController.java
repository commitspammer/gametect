package com.gametect.api.controller;

import com.gametect.api.model.Credentials;
import com.gametect.api.model.Token;
import com.gametect.api.model.User;

import com.gametect.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService service;

	@PostMapping("/register")
	public Token register(@RequestBody User u) {
		return service.register(u);
	}

	@PostMapping("/login")
	public Token login(@RequestBody Credentials c) {
		return service.login(c);
	}

}
