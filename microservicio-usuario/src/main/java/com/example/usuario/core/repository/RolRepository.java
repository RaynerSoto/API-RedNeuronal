package com.example.usuario.core.repository;

import com.auth0.jwt.interfaces.Claim;
import com.example.usuario.core.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    @Override
    Optional<Rol> findById(Long aLong);

    Long existsByNombreRolEquals(String nombreRol);

    Optional<Rol> findByNombreRolEquals(Claim rol);
}
