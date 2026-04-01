package com.example.generarpdf.core.feignClient;

import com.example.generarpdf.core.dto.ParticipanteDto;
import com.example.generarpdf.core.dto.UserName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservicio-usuario", url = "localhost:8085/api/v1/usuario")
public interface UsuarioParticipanteClient {

    @GetMapping("/obtenerUsuarioporNombre/{nombre}")
    UserName existeUsuariobynombre(@PathVariable String nombre);

}


