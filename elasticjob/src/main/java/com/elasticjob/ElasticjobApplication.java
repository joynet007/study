package com.elasticjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.elasticjob.demo")
public class ElasticjobApplication {

	public static void main(String[] args) {

		SpringApplication.run(ElasticjobApplication.class, args);
	}
}
