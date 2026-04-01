package com.example.generarpdf.core.feignClient;

import com.example.generarpdf.core.dto.PatologiaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="microservicio-patologia", url = "localhost:8088/api/v1/patologia")
public interface PatologiasClient {

    @GetMapping("/all")
    List<PatologiaDto> obtenerPatologias();
}
