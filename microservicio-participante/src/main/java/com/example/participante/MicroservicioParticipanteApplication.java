package com.example.participante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioParticipanteApplication {

  public static void main(String[] args) {
    SpringApplication.run(MicroservicioParticipanteApplication.class, args);
  }
}
