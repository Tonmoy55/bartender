package com.tonmoy.bartender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BartenderApplication {
	private static final Logger logger =  LoggerFactory.getLogger(BartenderApplication.class);
	public static void main(String[] args) {
		System.out.println("Current Directory = " + System.getProperty("user.dir"));
		SpringApplication.run(BartenderApplication.class, args);
		logger.info("just a test info log");
	}

}
