/**
 * 
 */
package com.smartjob.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smartjob.dto.ErrorMessageDto;
import com.smartjob.dto.UserDto;
import com.smartjob.dto.UserResponseDto;
import com.smartjob.exception.SmartJobBusinessLogicException;
import com.smartjob.service.UserServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador API Rest para usuarios
 * 
 * @author yadickson
 */
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Users", description = "API Restful de Usuarios - prueba Smart Jobs")
@RequestMapping("/api/users/")
@Slf4j
@RestController
public class UserRestController {

	private final UserServiceInterface userService;

	public UserRestController(UserServiceInterface userService) {
		this.userService = userService;
	}

	@Operation(summary = "Crear Usuario", description = "Crea un nuevo Usuario")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Ok", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request invalid paramters", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessageDto.class)) }) })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponseDto> saveUser(@RequestBody @Valid UserDto usuario)
			throws SmartJobBusinessLogicException {
		log.info("saveUser");

		UserResponseDto response = userService.addUser(usuario);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "Obtener Usuarios", description = "Obtiene una lista de todos los usuarios. Para ejecutar este endpoint necesita el token JWT de un usuario creado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Ok", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = List.class)) }) })
	@GetMapping
	public List<UserDto> getAllUsers() throws SmartJobBusinessLogicException {
		log.info("getAllUsers");
		return userService.getAllUsers();
	}

}
