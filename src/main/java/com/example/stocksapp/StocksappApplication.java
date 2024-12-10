package com.example.stocksapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class StocksappApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocksappApplication.class, args);
	}

	@Configuration
	public class WebConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			// Allow all origins
			registry.addMapping("/**")
					.allowedOrigins("http://localhost:4200") // Angular frontend
					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Allow HTTP methods just in case
		}
	}

}
