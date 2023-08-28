package com.gametect.api.controller;

import com.gametect.api.exception.*;
import com.gametect.api.model.Error;

import com.gametect.api.model.UserProject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.dao.EmptyResultDataAccessException;

@RestControllerAdvice
public class ApiControllerAdvice {

//	@ExceptionHandler(EntityAlreadyExistsException.class)
//	@ResponseStatus(HttpStatus.CONFLICT)
//	public ErrorDTO handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
//		return ErrorDTO.builder()
//			.status(HttpStatus.CONFLICT)
//			.message(e.getMessage())
//			.build();
//	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleResourceNotFoundException(ResourceNotFoundException exception) {
		Error e = new Error();
		e.status = HttpStatus.NOT_FOUND;
		e.message = exception.getMessage();
		return e;
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleUserNotFoundException(UserNotFoundException exception) {
		Error e = new Error();
		e.status = HttpStatus.NOT_FOUND;
		e.message = exception.getMessage();
		return e;
	}

	@ExceptionHandler(WrongPasswordException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error handleWrongPasswordException(WrongPasswordException exception) {
		Error e = new Error();
		e.status = HttpStatus.BAD_REQUEST;
		e.message = exception.getMessage();
		return e;
	}

	@ExceptionHandler(FrameworkNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleFrameworkNotFoundException(FrameworkNotFoundException exception) {
		Error e = new Error();
		e.status = HttpStatus.BAD_REQUEST;
		e.message = exception.getMessage();
		return e;
	}
	@ExceptionHandler(InUseEmailException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public Error handleEmailInUsedException(InUseEmailException exception) {
		Error e = new Error();
		e.status = HttpStatus.CONFLICT;
		e.message = exception.getMessage();
		return e;
	}
	@ExceptionHandler(ProjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleProjectNotFoundException(ProjectNotFoundException exception) {
		Error e = new Error();
		e.status = HttpStatus.NOT_FOUND;
		e.message = exception.getMessage();
		return e;
	}
	@ExceptionHandler(UserProjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleUserProjectNotFoundException(UserProjectNotFoundException exception) {
		Error e = new Error();
		e.status = HttpStatus.NOT_FOUND;
		e.message = exception.getMessage();
		return e;
	}
	@ExceptionHandler(UserProjectRepeatedException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public Error handleUserProjectRepeatedException(UserProjectRepeatedException exception) {
		Error e = new Error();
		e.status = HttpStatus.CONFLICT;
		e.message = exception.getMessage();
		return e;
	}
	@ExceptionHandler(UserProjectRoleRepeatedException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public Error handleUserProjectRoleRepeatedException(UserProjectRoleRepeatedException exception) {
		Error e = new Error();
		e.status = HttpStatus.CONFLICT;
		e.message = exception.getMessage();
		return e;
	}
	@ExceptionHandler(ObrigationsFieldNullException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error handleObrigationsFieldNullException(ObrigationsFieldNullException exception) {
		Error e = new Error();
		e.status = HttpStatus.BAD_REQUEST;
		e.message = exception.getMessage();
		return e;
	}

//	@ExceptionHandler(NonUniqueFieldException.class)
//	@ResponseStatus(HttpStatus.CONFLICT)
//	public ErrorDTO handleNonUniqueFieldException(NonUniqueFieldException e) {
//		return ErrorDTO.builder()
//			.status(HttpStatus.CONFLICT)
//			.message(e.getMessage())
//			.build();
//	}

//	@ExceptionHandler(EmptyResultDataAccessException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ErrorDTO handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
//		return ErrorDTO.builder()
//			.status(HttpStatus.NOT_FOUND)
//			.message(e.getMessage())
//			.build();
//	}
//
//	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public ErrorDTO handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
//		return ErrorDTO.builder()
//			.status(HttpStatus.BAD_REQUEST)
//			.message(e.getMessage())
//			.build();
//	}

}
