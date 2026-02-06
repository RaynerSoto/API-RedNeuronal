package com.example.microserviciodato.core.feingclient;

import com.example.microserviciodato.core.dto.PatologiaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "microservicio-patologiatologia", url = "localhost:8088/api/v1/patologia")
public interface PatologiaClient {

    /**
     * Obtiene una lista de patologías asociadas a un dato específico.
     * @param id_dato El ID del dato para el cual se desean obtener las patologías.
     * @return Una lista de objetos PatologiaDto que representan las patologías asociadas al dato.
     * @throws RuntimeException Si ocurre un error al comunicarse con el microservicio de patología.
     */
    @GetMapping("/obtenerPatologiaXDato/{id_dato}")
    List<PatologiaDto> obtenerPatologiaXDato(@PathVariable Long id_dato);

}
