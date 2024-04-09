/**
 * 
 */
package com.smartjob.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.smartjob.dto.ErrorMessageDto;

/**
 * Manejador de Excepciones
 * 
 * @author yadickson
 */
@RestControllerAdvice
public class SmartJobExceptionHandler {

	@ExceptionHandler(value = { SmartJobBusinessLogicException.class })
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public ErrorMessageDto getError(SmartJobBusinessLogicException ex, WebRequest request) {
		return ErrorMessageDto.builder().mensaje("Error logica del negocio, ver logs del sistema").build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		StringBuilder msgError = new StringBuilder("");

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			msgError.append("<campo:").append(fieldName).append(",").append("mensajeError:").append(errorMessage)
					.append(">").append(",");
		});
		return errors;
	}

}
