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
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=]).{8,12}$", message = "debe tener al menos una minuscula, al menos una mayuscula, al menos 1 digito, al menos un simbolo especial @#$%^&+=, minima longitud 8 y maximo 20")
	private String password;

	@NotNull(message = "telefonos no debe ser nulo")
	@Builder.Default
	private List<PhoneDto> phones = new ArrayList<>();

}
