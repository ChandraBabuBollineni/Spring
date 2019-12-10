package com.revature.course.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:8080","http://ec2-52-66-211-217.ap-south-1.compute.amazonaws.com:8080")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD").allowCredentials(true);
	}
}
