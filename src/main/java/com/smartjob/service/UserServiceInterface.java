/**
 * 
 */
package com.smartjob.service;

import java.util.List;
import java.util.Optional;
import com.smartjob.dto.UserDto;
import com.smartjob.dto.UserResponseDto;
import com.smartjob.exception.SmartJobBusinessLogicException;

/**
 * User service
 * 
 * @author yadickson
 */
public interface UserServiceInterface {

	/**
	 * Obtener usuario por email
	 * 
	 * @param email
	 * @return Optional<UserDto>
	 * @throws Exception
	 */
	Optional<UserDto> loadUserByEmail(String email) throws SmartJobBusinessLogicException;

	/**
	 * Agregar nuevo usuario
	 * 
	 * @param userParam
	 * @return UserDto
	 * @throws Exception
	 */
	UserResponseDto addUser(UserDto userParam) throws SmartJobBusinessLogicException;

	/**
	 * Obtener todos los usuarios
	 * 
	 * @return List<UserDto>
	 * @throws Exception
	 */
	List<UserDto> getAllUsers() throws SmartJobBusinessLogicException;

}
