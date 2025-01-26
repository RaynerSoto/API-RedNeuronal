package com.example.patologia.core.feingclient;

import com.example.patologia.core.dto.DatoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "microservicio-dato", url = "localhost:8087/api/v1/datoparticipante")
public interface DatoClient {
    @GetMapping("/findbyid/{id}")
    DatoDto existeDato(Long id);
}
