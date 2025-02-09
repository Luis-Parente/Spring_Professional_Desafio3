package com.devsuperior.desafio3.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.desafio3.dto.CustomErrorDTO;
import com.devsuperior.desafio3.dto.ValidationErrorDTO;
import com.devsuperior.desafio3.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomErrorDTO customError = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(customError);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomErrorDTO> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationErrorDTO err = new ValidationErrorDTO(Instant.now(), status.value(), "Invalid data",
				request.getRequestURI());

		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}

		return ResponseEntity.status(status).body(err);
	}

}
