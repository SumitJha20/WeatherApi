package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
		System.out.println("Running...");
	}
	@Configuration
	public class AppConfig {

	    @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
	}

}
