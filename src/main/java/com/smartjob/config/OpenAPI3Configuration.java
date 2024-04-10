/**
 * 
 */
package com.smartjob.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Open API Configuration
 * @author yadickson
 */
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@Configuration
public class OpenAPI3Configuration {

	@Autowired
	private SwaggerProperties swaggerProperties;

	@Bean
	OpenAPI customOpenAPIInfo() {
		return new OpenAPI().info(new Info().title(swaggerProperties.getApplicationTitle())
				.description(swaggerProperties.getApplicationDescription())
				.version(swaggerProperties.getApplicationVersion())
				.termsOfService(swaggerProperties.getTermsOfService())
				.license(new License().name(swaggerProperties.getLicense()).url(swaggerProperties.getLicenseUrl()))
				.contact(new Contact().email(swaggerProperties.getContactEmail())
						.name(swaggerProperties.getContactName())));
	}

}
