/**
 * 
 */
package com.smartjob.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Role
 * @author yadickson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {

	private BigInteger id;
	private String name;

}
