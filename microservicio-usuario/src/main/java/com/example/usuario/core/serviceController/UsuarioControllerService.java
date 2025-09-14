package com.example.usuario.core.serviceController;

import com.example.usuario.core.dto.UsuarioDto;
import com.example.usuario.core.dto.UsuarioDtoSent;
import com.example.usuario.core.exception.SearchException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UsuarioControllerService {
    public String insertarUsuario(UsuarioDto usuario, HttpServletRequest request) throws SearchException;
    public String modificarUsuario(UsuarioDto usuarioDto,Long id,HttpServletRequest request) throws SearchException;
    public String eliminarUsuario(Long id,HttpServletRequest request) throws SearchException;
    public List<UsuarioDtoSent> listarUsuarios(HttpServletRequest request) throws SearchException;
    public UsuarioDtoSent obtenerDatosPorId(Long id,HttpServletRequest request) throws SearchException;
    public boolean existeUsuariobynombre(Long id, HttpServletRequest request) throws SearchException;
}
