/**
 * 
 */
package com.smartjob.service;

import java.util.List;


import com.smartjob.dto.RoleDto;
import com.smartjob.exception.SmartJobBusinessLogicException;

/**
 * Role Service
 * 
 * @author yadickson
 */
public interface RoleServiceInterface {

	/**
	 * Agrega nuevo Rol de usuario
	 * 
	 * @param role
	 * @return boolean
	 */
	boolean add(RoleDto role) throws SmartJobBusinessLogicException;

	/**
	 * Obtiene los roles de un usuario
	 * @param userEmail
	 * @return List<RoleDto>
	 * @throws Exception
	 */
	List<RoleDto> getRolesByUserEmail(String userEmail) throws SmartJobBusinessLogicException;
	
	/**
	 * Obtiene un rol por nombre
	 * @param roleName
	 * @return
	 * @throws SmartJobBusinessLogicException
	 */
	RoleDto getRolesByName(String roleName) throws SmartJobBusinessLogicException;

}
