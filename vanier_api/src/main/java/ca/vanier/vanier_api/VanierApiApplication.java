package ca.vanier.vanier_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
//Scan entities and any components needed to run the api
@EntityScan(basePackages = {"ca.vanier.vanierapi_lib.entities"})
@ComponentScan(basePackages = {"ca.vanier.vanierapi_lib.controllers", "ca.vanier.vanierapi_lib.services", "ca.vanier.vanierapi_lib.utils",
								"ca.vanier.vanier_api.controller", "ca.vanier.vanier_api.service", "ca.vanier.vanier_api.security"})
@EnableJpaRepositories(basePackages = {"ca.vanier.vanierapi_lib.repositories"})
public class VanierApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(VanierApiApplication.class, args);
	}

}
