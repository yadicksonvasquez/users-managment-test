/**
 * 
 */
package com.smartjob.config;

import static java.util.Optional.ofNullable;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.smartjob.service.SmartJobUserDetailsService;
import com.smartjob.util.JwtTokenUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT Token filter
 * 
 * @author yadickson
 */
@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtTokenUtil jwtTokenUtil;

	private final SmartJobUserDetailsService userDetailsService;

	/**
	 * 
	 */
	public JwtTokenFilter(JwtTokenUtil jwtTokenUtil, SmartJobUserDetailsService userDetailsService) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("doFilterInternal|request:" + request);

		// validate if header has AUTHORIZATION if not return error 401 Unauthorized
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (!StringUtils.hasLength(header) || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		// Get jwt token and validate
		final String token = header.split(" ")[1].trim();
		if (!jwtTokenUtil.validate(token)) {
			filterChain.doFilter(request, response);
			return;
		}

		// Get user identity and set it on the spring security context
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtTokenUtil.getUserEmail(token));

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				ofNullable(userDetails).map(UserDetails::getAuthorities).orElse(Arrays.asList()));

		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);

	}

}
