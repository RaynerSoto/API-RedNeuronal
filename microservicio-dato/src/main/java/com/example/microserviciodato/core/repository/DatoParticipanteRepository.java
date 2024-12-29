package com.example.microserviciodato.core.repository;

import com.example.microserviciodato.core.entities.DatoParticipante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatoParticipanteRepository extends JpaRepository<DatoParticipante,Long> {
    List<DatoParticipante> findAllByIdparticipante(Long id);
}
