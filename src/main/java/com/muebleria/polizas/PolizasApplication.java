package com.muebleria.polizas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PolizasApplication {

	String home(){
		return "Hello World";
	}

	public static void main(String[] args) {
		SpringApplication.run(PolizasApplication.class, args);
		System.out.println("Arrancando API REST Mueblería");
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		String[] allowedMethods = { "GET", "POST", "PUT", "DELETE", "OPTIONS" };
    	String[] allowedHeaders = { "Authorization", "Content-Type" };
    	String[] allowDomains = { "http://localhost:4200" };

		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedOrigins(allowDomains)
					.allowedMethods(allowedMethods)
					.allowedHeaders(allowedHeaders)
					.allowCredentials(true);
			}
		
		};
	}

}