package com.example.generarpdf.core.service;


import com.example.generarpdf.core.dto.PatologiaDto;
import com.example.generarpdf.core.http.response.ParticipanteResponse;

import java.util.List;

public interface ParticipantesUsuarioService {

    ParticipanteResponse obtenerparticipantes(String nombre);
    byte[] generarPdf(String nombre) throws Exception;
    List<PatologiaDto> obtenerpatologias();

}
