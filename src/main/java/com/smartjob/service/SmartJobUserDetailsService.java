/**
 * 
 */
package com.smartjob.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import com.smartjob.dto.RoleDto;
import com.smartjob.dto.UserDto;
import lombok.extern.slf4j.Slf4j;

/**
 * UserDetailsService implements
 */
@Slf4j
@Service
public class SmartJobUserDetailsService implements UserDetailsService {

	private final UserServiceInterface userService;
	private final RoleServiceInterface roleService;

	/**
	 * 
	 */
	public SmartJobUserDetailsService(UserServiceInterface userService, RoleServiceInterface roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Optional<UserDto> userInfo = userService.loadUserByEmail(username);
			if (userInfo.isEmpty()) {
				throw new UsernameNotFoundException("User " + username);
			} else {
				UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
				builder.disabled(false);
				builder.password(userInfo.get().getPassword());
				// set Roles
				builder.authorities(this.getAuthorities(username));
				return builder.build();
			}
		} catch (Exception e) {
			log.error("Error", e);
		}
		return null;
	}

	private List<GrantedAuthority> getAuthorities(String username) {
		List<GrantedAuthority> result = new ArrayList<>();
		try {
			log.info("getAuthorities- Getting Authorities, username: {}", username);

			// get roles info
			List<RoleDto> roles = roleService.getRolesByUserEmail(username);
			result.addAll(roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
					.collect(Collectors.toList()));

		} catch (Exception e) {
			log.error("Error", e);
		}
		return result;
	}

}
