/**
 * 
 */
package com.smartjob.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smartjob.dto.UserDto;
import com.smartjob.dto.UserResponseDto;
import com.smartjob.exception.SmartJobBusinessLogicException;
import com.smartjob.service.UserServiceInterface;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador API Rest para usuarios
 * 
 * @author yadickson
 */
@RequestMapping("/api/users/")
@Slf4j
@RestController
public class UserRestController {

	private final UserServiceInterface userService;

	public UserRestController(UserServiceInterface userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<UserResponseDto> saveUser(@RequestBody @Valid UserDto usuario)
			throws SmartJobBusinessLogicException {
		log.info("saveUser");

		UserResponseDto response = userService.addUser(usuario);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public List<UserDto> getAllUsers() throws SmartJobBusinessLogicException {
		log.info("getAllUsers");
		return userService.getAllUsers();
	}

}
