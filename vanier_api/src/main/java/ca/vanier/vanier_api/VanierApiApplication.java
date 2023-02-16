package ca.vanier.vanier_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// @ComponentScan(basePackages = {"ca.vanier.course_lib.controller", "ca.vanier.course_lib.entity", 
// 								"ca.vanier.teacher_lib.controller", "ca.vanier.teacher_lib.entity", 
// 								"ca.vanier.student_lib.controller", "ca.vanier.student_lib.entity",
// 								"ca.vanier.course_lib.repository","ca.vanier.course_lib.service"
// 								})
@SpringBootApplication
@EntityScan(basePackages = {"ca.vanier.course_lib.entity", "ca.vanier.teacher_lib.entity", "ca.vanier.student_lib.entity"})
@ComponentScan(basePackages = {"ca.vanier.course_lib.controller", "ca.vanier.teacher_lib.controller", "ca.vanier.student_lib.controller",
								"ca.vanier.course_lib.service", "ca.vanier.student_lib.service", "ca.vanier.teacher_lib.service",
								"ca.vanier.vanier_api.controller", "ca.vanier.vanier_api.service", "ca.vanier.vanier_api.security"})
@EnableJpaRepositories(basePackages = {"ca.vanier.course_lib.repository", "ca.vanier.teacher_lib.repository", "ca.vanier.student_lib.repository", })
public class VanierApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(VanierApiApplication.class, args);
	}

}
