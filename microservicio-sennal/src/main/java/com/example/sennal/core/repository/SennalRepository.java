package com.example.sennal.core.repository;

import com.example.sennal.core.entities.Sennal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SennalRepository extends JpaRepository<Sennal, Long> {
    List<Sennal> findAllByIddato(Long id_dato);
}
