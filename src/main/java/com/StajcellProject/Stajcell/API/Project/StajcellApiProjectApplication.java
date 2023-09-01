package com.StajcellProject.Stajcell.API.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.StajcellProject.Stajcell.API.Project.LoggingDemo;


@SpringBootApplication
public class StajcellApiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(StajcellApiProjectApplication.class, args);
		
		LoggingDemo loggingDemo = new LoggingDemo();

		loggingDemo.doSomething();
		

	}

}
