package com.pmh.secureapp;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.pmh.Domains")
@ComponentScan({"com.pmh.Controllers","com.pmh.Service","com.pmh,secureapp"})
@EnableJpaRepositories("com.pmh.Repositories")
public class SecureAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureAppApplication.class, args);
	
	
	}

}
