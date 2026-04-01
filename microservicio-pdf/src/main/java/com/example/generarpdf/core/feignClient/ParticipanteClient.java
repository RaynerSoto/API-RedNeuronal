package com.example.generarpdf.core.feignClient;

import com.example.generarpdf.core.dto.ParticipanteDto;
import com.example.generarpdf.core.dto.ParticipantesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservicio-participante", url = "localhost:8086/api/v1/participante")
public interface ParticipanteClient {

    @GetMapping("/obtenerParticipanteXUsuario/{id}")
    List<ParticipantesDto> obtenerParticipanteXUsuario(@PathVariable Long id);

    @GetMapping("/getIdbyParticipantebyCi/{ci}")
    Long existeParticipantebyCi(@PathVariable String ci);

    @GetMapping("/obtenerDatoXParticipante/{idparticipante}")
    ParticipanteDto obtenerDatoXParticipante(@PathVariable Long idparticipante);
}
