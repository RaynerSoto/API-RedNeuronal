package com.example.usuario.core.dto;

import com.example.usuario.core.model.Usuario;

public record UsuarioDtoSent(Long id,
                             String rol,
                             String nombre,
                             String username,
                             String contrasenna,
                             String noprofesional) {
    public static UsuarioDtoSent fromUsuario(Usuario usuario) {
        return new UsuarioDtoSent(usuario.getId(),usuario.getIdrol().getNombreRol(),usuario.getNombre(),usuario.getUsername(),usuario.getContrasenna(),usuario.getNoprofesional());
    }
}
