package com.empacotamento.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
	private LocalDateTime timestamp;
	private int status;
	private String error;
	private List<ValidationErrorResponse> details;
}