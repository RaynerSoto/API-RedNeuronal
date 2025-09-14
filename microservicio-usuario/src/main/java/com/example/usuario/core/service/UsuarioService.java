package com.example.usuario.core.service;

import com.example.usuario.core.dto.UsuarioDto;
import com.example.usuario.core.model.Rol;
import com.example.usuario.core.model.Usuario;
import com.example.usuario.core.exception.SearchException;
import com.example.usuario.core.http.response.ParticipantesByUsuarioResponse;


import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    void insertarUsuario(Usuario usuario);
    void modificarUsuario(UsuarioDto usuario, Long id, Rol rol) throws SearchException;
    void eliminarUsuario(Long id) throws SearchException;
    List<Usuario> obtenerUsuarios();
    Optional<Usuario> obtenerUsuarioXId(Long id) throws SearchException;
    Usuario obtenerUsuarioXNombre(String nombre) throws SearchException;
    boolean existeUsuario(Long id) throws SearchException;
    Long existeUsuariobynombre(String nombre) throws SearchException;
    ParticipantesByUsuarioResponse obtenerParticipanteXUsuario(Long idUsuario);

}
