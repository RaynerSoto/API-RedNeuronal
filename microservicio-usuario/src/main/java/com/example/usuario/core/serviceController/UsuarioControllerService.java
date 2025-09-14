package com.example.usuario.core.serviceController;

import com.example.usuario.core.dto.UsuarioDto;
import com.example.usuario.core.exception.SearchException;
import jakarta.servlet.http.HttpServletRequest;

public interface UsuarioControllerService {
    public String insertarUsuario(UsuarioDto usuario, HttpServletRequest request) throws SearchException;
    public String modificarUsuario(UsuarioDto usuarioDto,HttpServletRequest request) throws SearchException;
    public List<Usua>
}
