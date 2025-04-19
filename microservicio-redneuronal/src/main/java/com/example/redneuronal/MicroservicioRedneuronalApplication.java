package com.example.redneuronal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicioRedneuronalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioRedneuronalApplication.class, args);
	}

}
