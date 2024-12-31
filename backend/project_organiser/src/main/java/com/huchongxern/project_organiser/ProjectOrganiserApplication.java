package com.huchongxern.project_organiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProjectOrganiserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectOrganiserApplication.class, args);
	}

	@GetMapping("/") // get endpoint
	public String apiRoot(){
		return "HELLO WORLD";
	}

}
