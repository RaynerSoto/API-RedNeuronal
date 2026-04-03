package com.example.participante.core.repository;

import com.example.participante.core.model.Participante;
import com.example.participante.core.model.UsuarioParticipante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioParticipanteRepository extends JpaRepository<UsuarioParticipante,Long> {
    @Query(
            "SELECT p FROM Participante p JOIN UsuarioParticipante up ON p.id = up.id_participante " +
                    "WHERE up.id_usuario = :id_usuario")
    List<Participante> findAllByIdUser(Long id_usuario);
}
