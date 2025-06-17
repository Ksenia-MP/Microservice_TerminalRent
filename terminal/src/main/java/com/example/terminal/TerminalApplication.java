package com.example.terminal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TerminalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TerminalApplication.class, args);
	}

}
