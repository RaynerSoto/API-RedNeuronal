package com.example.participante.core.repository;

import com.example.participante.core.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Optional<Participante> findByCi(String ci);
    @Query("Select p.id from Participante p where p.ci = :ci")
    Long getIdByCi(String ci);
    boolean existsByCi(String ci);

}
