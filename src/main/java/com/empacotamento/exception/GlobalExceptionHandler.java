package com.empacotamento.exception;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<ValidationErrorResponse> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> new ValidationErrorResponse(error.getField(), error.getDefaultMessage()))
				.collect(Collectors.toList());

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"Validation Failed", errors);

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
		String errorMessage = "Formato de requisição JSON inválido ou campo com tipo incorreto.";
		String fieldName = null;

		if (ex.getCause() instanceof MismatchedInputException mismatchedEx) {
			if (mismatchedEx.getPath() != null && !mismatchedEx.getPath().isEmpty()) {
				fieldName = mismatchedEx.getPath().get(mismatchedEx.getPath().size() - 1).getFieldName();
				errorMessage = "Campo '" + fieldName + "': valor com formato inválido.";
				if (mismatchedEx.getTargetType() != null) {
					errorMessage += " Esperado tipo " + mismatchedEx.getTargetType().getSimpleName() + ".";
				}
			} else {
				errorMessage = "Valor com formato inválido para um campo.";
			}
		}

		List<ValidationErrorResponse> details = null;
		if (fieldName != null) {
			details = List.of(new ValidationErrorResponse(fieldName, errorMessage));
		} else {
			details = List.of(new ValidationErrorResponse("json", errorMessage));
		}

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"Bad Request - Deserialization Error", details);

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex) {
		String path = ex.getResourcePath();
		String errorMessage = "O recurso ou endpoint '" + path + "' não foi encontrado.";

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), errorMessage,
				Collections.emptyList()
		);

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			WebRequest request) {
		String errorMessage = "Tipo de mídia não suportado para esta requisição. Tipos suportados: "
				+ ex.getSupportedMediaTypes();

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),

				"Unsupported Media Type", List.of(new ValidationErrorResponse("content_type", errorMessage)));

		return new ResponseEntity<>(errorDetails, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
}