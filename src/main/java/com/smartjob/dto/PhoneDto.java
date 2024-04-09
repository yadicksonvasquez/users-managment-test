/**
 * 
 */
package com.smartjob.dto;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class PhoneDto {
	
	private BigInteger id;

	@JsonProperty("number")
	private String phoneNumber;

	@JsonProperty("citycode")
	private String cityCode;

	@JsonProperty("countrycode")
	private String countryCode;

}
