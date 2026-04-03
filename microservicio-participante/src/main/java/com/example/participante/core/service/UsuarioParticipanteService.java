package com.example.participante.core.service;

import com.example.participante.core.model.Participante;
import com.example.participante.core.model.UsuarioParticipante;
import com.example.participante.core.exception.SearchException;

import java.util.List;

public interface UsuarioParticipanteService {
    void crearRelacion(UsuarioParticipante usuarioParticipante);
    List<Participante> obtenerParticipanteXUsuario(Long id_usuario) throws SearchException;
}
