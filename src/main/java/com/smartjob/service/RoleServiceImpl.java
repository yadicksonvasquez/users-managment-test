/**
 * 
 */
package com.smartjob.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.smartjob.dto.RoleDto;
import com.smartjob.exception.SmartJobBusinessLogicException;
import com.smartjob.model.Role;
import com.smartjob.model.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Role service implement
 * 
 * @author yadickson
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleServiceInterface {

	private final RoleRepository repository;

	/**
	 * 
	 */
	public RoleServiceImpl(RoleRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean add(RoleDto role) throws SmartJobBusinessLogicException {
		try {
			log.info("add Role");
			Role entity = Role.builder().name(role.getName()).build();
			this.repository.save(entity);
			return true;
		} catch (Exception e) {
			log.error("Error", e);
			throw new SmartJobBusinessLogicException("Error", e);
		}

	}

	@Override
	public List<RoleDto> getRolesByUserEmail(String userEmail) throws SmartJobBusinessLogicException {
		try {
			log.info("getRolesByUserEmail- email:{}", userEmail);
			List<Role> roles = this.repository.getRoleByUserEmail(userEmail);
			return roles.stream().map(role -> RoleDto.builder().id(role.getId()).name(role.getName()).build())
					.collect(Collectors.toList());
		} catch (Exception e) {
			log.error("Error", e);
			throw new SmartJobBusinessLogicException("Error", e);
		}
	}

	@Override
	public RoleDto getRolesByName(String roleName) throws SmartJobBusinessLogicException {
		try {
			log.info("getRolesByName- roleName:{}", roleName);
			Role role = this.repository.getRoleByName(roleName);
			return RoleDto.builder().id(role.getId()).name(role.getName()).build();
		} catch (Exception e) {
			log.error("Error", e);
			throw new SmartJobBusinessLogicException("Error", e);
		}
	}

}
