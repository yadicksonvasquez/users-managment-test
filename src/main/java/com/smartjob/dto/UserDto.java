/**
 * 
 */
package com.smartjob.dto;

import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	@Email(regexp = ".+[@]dominio.cl", message = "El correo debe tener el formato aaaaaaa@dominio.cl")
	private String email;

	@NotNull(message = "password no debe ser nula")
	@NotEmpty(message = "password no debe ser vacio")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "debe tener al menos una minuscula [a-z], al menos una mayuscula [A-Z], al menos 1 digito [0-9], al menos un simbolo especial  ! @ # & ( ), debe contener minimo longitud 8 y maximo 20 caracteres")
	private String password;

	@NotNull(message = "telefonos no debe ser nulo")
	@Builder.Default
	private List<PhoneDto> phones = new ArrayList<>();

}
