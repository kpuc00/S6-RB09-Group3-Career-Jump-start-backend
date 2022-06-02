package com.bezkoder.spring.login;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class SpringBootLoginExampleApplication {

	public static void main(String[] args) throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
		SpringApplication.run(SpringBootLoginExampleApplication.class, args);
	}
}
