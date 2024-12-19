package com.example.usuario.core.feignClient;

import com.example.usuario.core.dto.ParticipanteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservicio-participante", url = "localhost:8086/api/v1/participante")
public interface ParticipanteClient {
    @GetMapping("/obtenerParticipanteXUsuario/{id}")
    List<ParticipanteDto> obtenerParticipanteXUsuario(@PathVariable Long id);
}
