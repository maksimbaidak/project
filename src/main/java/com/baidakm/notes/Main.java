package com.baidakm.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		SpringApplication app = new SpringApplication(Main.class);
		app.run();
	}
}
