package com.sg.classroster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
public class ClassrosterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassrosterApplication.class, args);
	}

}
