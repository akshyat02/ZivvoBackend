package com.zivvo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZivvoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZivvoApiApplication.class, args);
		System.out.println("Application Started...");
	}

}
