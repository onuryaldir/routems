package org.development.routems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableDiscoveryClient
public class RoutemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoutemsApplication.class, args);
	}

}
