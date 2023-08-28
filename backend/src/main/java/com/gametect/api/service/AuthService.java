package com.gametect.api.service;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.Optional;

import com.gametect.api.model.Credentials;
import com.gametect.api.model.Token;
import com.gametect.api.model.User;
import com.gametect.api.exception.WrongPasswordException;
import com.gametect.api.exception.UserNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AuthService {

	@Autowired
	private UserService userService;

	private String placeholder = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxIn0.rTCH8cLoGxAm_xw68z-zXVKi9ie6xJn9tnVWjd_9ftE";

	public Token register(User u) {
		User user = userService.create(u); //could throw
		Token t = new Token();
		t.token = placeholder;
		t.user = user;
		return t;
	}

	public Token login(Credentials c) {
		User user = userService.getOne(u -> u.getEmail().equals(c.email))
			.orElseThrow(() -> new UserNotFoundException());
		if (!user.getPassword().equals(c.password))
			throw new WrongPasswordException();
		Token t = new Token();
		t.token = placeholder;
		t.user = user;
		return t;
	}

}
