/**
 * 
 */
package com.smartjob.dto;

import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Usuario
 * 
 * @author yadickson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

	@NotNull(message = "nombre no debe ser nulo")
	@NotEmpty(message = "nombre no debe ser vacio")
	private String name;

	@NotNull(message = "correo no debe ser nulo")
	@NotEmpty(message = "correo no debe ser vacio")
	@Email(regexp = ".+[@].+[\\.].+")
	private String email;

	@NotNull(message = "password no debe ser nula")
	@NotEmpty(message = "password no debe ser vacio")
	private String password;

	@NotNull(message = "telefonos no debe ser nulo")
	@Builder.Default
	private List<PhoneDto> phones = new ArrayList<>();

}
