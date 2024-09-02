package com.project.ets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
@OpenAPIDefinition
public class AppDoc {

	Info info() {
		return new Info().title("Education Tracking System- API").description("a simple project build with api endpoints for suggest the recruiters for student selection process, and for update"
				+ "ratings and see the ratings as well");
	}
	
	@Bean
	OpenAPI openApi() {
		return new OpenAPI().info(info());
	}
}
