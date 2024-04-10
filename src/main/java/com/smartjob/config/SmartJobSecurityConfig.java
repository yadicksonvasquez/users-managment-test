/**
 * 
 */
package com.smartjob.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security Configuration
 * 
 * @author yadickson
 */
@Configuration
@EnableWebSecurity
public class SmartJobSecurityConfig {

	@Autowired
	private JwtTokenFilter jwtTokenFilter;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> authz.requestMatchers("/h2-console/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/users/").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/users/").hasRole(AppConstants.DEFAULT_USER_ROLE)
				.requestMatchers("/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
				.anyRequest().authenticated());
		http.csrf((csrf) -> csrf.disable());
		http.headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()));
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		http.exceptionHandling(
				(exp) -> exp.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
		return http.build();
	}
	
	@Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui.html", "/swagger-ui/**");
    }

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
