package com.example.usuario.core.service;

import com.example.usuario.core.entities.Usuario;
import com.example.usuario.core.exception.SearchException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    void insertarUsuario(Usuario usuario);
    void modificarUsuario(Usuario usuario) throws SearchException;
    void eliminarUsuario(Long id) throws SearchException;
    List<Usuario> obtenerUsuarios();
    Optional<Usuario> obtenerUsuarioXId(Long id) throws SearchException;
}
