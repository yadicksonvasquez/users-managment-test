/**
 * 
 */
package com.smartjob.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User response
 * @author yadickson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

	private BigInteger id;

	private LocalDateTime created;

	private LocalDateTime modified;

	@JsonProperty("last_login")
	private LocalDateTime lastLogin;

	private String token;

	@JsonProperty("isactive")
	private Boolean isActive;

}
