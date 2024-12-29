package com.example.patologia.core.repository;

import com.example.patologia.core.entities.Patologia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatologiaRepository extends JpaRepository<Patologia, Long> {
}
