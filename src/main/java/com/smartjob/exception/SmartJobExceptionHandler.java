/**
 * 
 */
package com.smartjob.exception;

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
		return ErrorMessageDto.builder().mensaje("Error logica del negocio:" + ex.getMessage()).build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorMessageDto handleValidationExceptions(MethodArgumentNotValidException ex) {
		StringBuilder msgError = new StringBuilder("");

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			msgError.append("<campo:").append(fieldName).append(", ").append("mensaje:").append(errorMessage)
					.append(">").append(",");
		});
		return ErrorMessageDto.builder().mensaje(msgError.toString()).build();
	}

}
