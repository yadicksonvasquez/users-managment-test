/**
 * 
 */
package com.smartjob.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Swagger properties
 * @author yadickson
 */
@Data
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {

	private String applicationDescription;
	private String applicationVersion;
	private String applicationTitle;
	private String termsOfService;
	private String license;
	private String licenseUrl;
	private String contactName;
	private String contactEmail;

}
