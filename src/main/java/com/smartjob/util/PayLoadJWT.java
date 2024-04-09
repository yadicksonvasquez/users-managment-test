/**
 * 
 */
package com.smartjob.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayLoadJWT {

	private String iss;
	private String sub;
	private int exp;
	private int iat;

}
