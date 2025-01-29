package com.example.sennal.core.repository;

import com.example.sennal.core.entities.Sennal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SennalRepository extends JpaRepository<Sennal, Long> {
}
