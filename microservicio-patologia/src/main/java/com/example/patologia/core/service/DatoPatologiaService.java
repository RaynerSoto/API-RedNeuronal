package com.example.patologia.core.service;

import com.example.patologia.core.dto.DatoPatologiaDto;
import com.example.patologia.core.entities.DatoPatologia;
import com.example.patologia.core.entities.Patologia;
import com.example.patologia.core.exception.SearchException;

import java.util.List;

public interface DatoPatologiaService {
    void crearRelacion(DatoPatologia datoPatologia);
    List<Patologia> obtenerPatologiaXDato(Long id_dato) throws SearchException;
}
