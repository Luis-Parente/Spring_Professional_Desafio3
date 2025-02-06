package com.devsuperior.desafio3.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO extends CustomErrorDTO {

	private List<FieldErrorDTO> errors = new ArrayList<>();

	public ValidationErrorDTO(Instant timestamp, Integer status, String error, String path) {
		super(timestamp, status, error, path);
	}

	public List<FieldErrorDTO> getErrors() {
		return errors;
	}

	public void addError(String field, String message) {
		errors.add(new FieldErrorDTO(field, message));
	}

}
