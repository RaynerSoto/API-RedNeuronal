package com.example.participante.core.repository;

import com.example.participante.core.entities.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Optional<Participante> findByCi(String ci);
}
