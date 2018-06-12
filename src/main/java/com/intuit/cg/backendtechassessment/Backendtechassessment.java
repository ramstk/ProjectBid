package com.intuit.cg.backendtechassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
@EnableHypermediaSupport(type = HypermediaType.HAL)
@SpringBootApplication
@EnableAutoConfiguration
public class Backendtechassessment {

	public static void main(String[] args) {
		SpringApplication.run(Backendtechassessment.class, args);
	}
}
