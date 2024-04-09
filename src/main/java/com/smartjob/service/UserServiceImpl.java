/**
 * 
 */
package com.smartjob.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.smartjob.config.AppConstants;
import com.smartjob.dto.PhoneDto;
import com.smartjob.dto.RoleDto;
import com.smartjob.dto.UserDto;
import com.smartjob.dto.UserResponseDto;
import com.smartjob.exception.SmartJobBusinessLogicException;
import com.smartjob.model.Phone;
import com.smartjob.model.Role;
import com.smartjob.model.User;
import com.smartjob.model.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * User service implements
 * 
 * @author yadickson
 */
@Slf4j
@Service
public class UserServiceImpl implements UserServiceInterface {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final RoleServiceInterface roleService;

	/**
	 * 
	 */
	public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder,
			RoleServiceInterface roleService) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.roleService = roleService;
	}

	@Override
	public Optional<UserDto> loadUserByEmail(String email) throws SmartJobBusinessLogicException {
		try {
			log.info("loadUserByEmail- email:{}", email);
			User entity = repository.findByEmail(email);
			if (entity != null) {
				UserDto userDto = UserDto.builder().name(entity.getName()).email(entity.getEmail())
						.password(entity.getPassword())
						.phones(entity.getPhones().stream()
								.map(phone -> PhoneDto.builder().id(phone.getId()).phoneNumber(phone.getPhoneNumber())
										.cityCode(phone.getCityCode()).countryCode(phone.getCountryCode()).build())
								.collect(Collectors.toList()))
						.build();
				return Optional.of(userDto);
			}
		} catch (Exception e) {
			log.error("Error", e);
			throw new SmartJobBusinessLogicException(e);
		}
		return Optional.empty();
	}

	@Override
	public UserResponseDto addUser(UserDto userParam) throws SmartJobBusinessLogicException {
		try {
			log.info("addUser");
			Optional<UserDto> userOptional = loadUserByEmail(userParam.getEmail());
			if (userOptional.isPresent()) {
				throw new SmartJobBusinessLogicException("El correo ya esta registrado");
			} else {
				User userEntity = User.builder().name(userParam.getName()).email(userParam.getEmail())
						.password(passwordEncoder.encode(userParam.getPassword())).isActive(true)
						.lastLogin(LocalDateTime.now()).created(LocalDateTime.now()).modified(LocalDateTime.now())
						.build();

				// set phones
				this.setPhones(userEntity, userParam);

				// set default role
				this.setDefaultRole(userEntity);

				userEntity = this.repository.save(userEntity);

				return UserResponseDto.builder().id(userEntity.getUserId()).created(userEntity.getCreated())
						.modified(userEntity.getModified()).lastLogin(userEntity.getLastLogin()).build();
			}
		} catch (Exception e) {
			log.error("Error", e);
			if (e instanceof SmartJobBusinessLogicException) {
				throw e;
			} else {
				throw new SmartJobBusinessLogicException(e);
			}
		}
	}

	/**
	 * Set user phones
	 * 
	 * @param userEntity
	 * @param userParam
	 */
	private void setPhones(User userEntity, UserDto userParam) {
		List<Phone> phones = new ArrayList<>();
		for (PhoneDto phone : userParam.getPhones()) {
			phones.add(Phone.builder().phoneNumber(phone.getPhoneNumber()).cityCode(phone.getCityCode())
					.countryCode(phone.getCountryCode()).user(userEntity).build());
		}
		userEntity.setPhones(phones);
	}

	/**
	 * Set default user Role
	 * 
	 * @param userEntity
	 * @throws SmartJobBusinessLogicException
	 */
	private void setDefaultRole(User userEntity) throws SmartJobBusinessLogicException {
		try {
			RoleDto role = this.roleService.getRolesByName(AppConstants.DEFAULT_USER_ROLE);
			userEntity.setRoles(Arrays.asList(
					Role.builder().id(role.getId()).name(role.getName()).users(Arrays.asList(userEntity)).build()));
		} catch (SmartJobBusinessLogicException e) {
			log.error("Error", e);
			throw new SmartJobBusinessLogicException(e);
		}
	}

	@Override
	public List<UserDto> getAllUsers() throws SmartJobBusinessLogicException {
		try {
			log.info("getAllUsers");
			List<User> userList = this.repository.findAll();
			return userList.stream().map(entity -> UserDto.builder().name(entity.getName()).email(entity.getEmail())
					.password(entity.getPassword())
					.phones(entity.getPhones().stream()
							.map(phone -> PhoneDto.builder().id(phone.getId()).phoneNumber(phone.getPhoneNumber())
									.cityCode(phone.getCityCode()).countryCode(phone.getCountryCode()).build())
							.collect(Collectors.toList()))
					.build()).collect(Collectors.toList());
		} catch (Exception e) {
			log.error("Error", e);
			throw new SmartJobBusinessLogicException("Error", e);
		}
	}

}
