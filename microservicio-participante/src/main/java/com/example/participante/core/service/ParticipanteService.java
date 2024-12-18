package com.example.participante.core.service;

import com.example.participante.core.entities.Participante;
import com.example.participante.core.exception.SearchException;

import java.util.List;
import java.util.Optional;

public interface ParticipanteService {
    void insertarParticipante(Participante participante);
    void modificarParticipante(Participante participante) throws SearchException;
    void eliminarParticipante(Long id) throws SearchException;
    List<Participante> obtenerParticipantes();
    Optional<Participante> obtenerParticipanteXId(Long id) throws SearchException;
    Optional<Participante> obtenerParticipanteXCI(String ci) throws SearchException;
}
