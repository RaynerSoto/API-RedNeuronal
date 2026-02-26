package com.example.usuario.core.serviceController.serviceControllerImpl;

import com.example.usuario.core.controller.UsuarioController;
import com.example.usuario.core.dto.UsuarioDto;
import com.example.usuario.core.dto.UsuarioDtoSent;
import com.example.usuario.core.exception.SearchException;
import com.example.usuario.core.model.Rol;
import com.example.usuario.core.model.Usuario;
import com.example.usuario.core.repository.RolRepository;
import com.example.usuario.core.repository.UsuarioRepository;
import com.example.usuario.core.service.LogsService;
import com.example.usuario.core.service.UsuarioService;
import com.example.usuario.core.serviceController.UsuarioControllerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UsuarioControllerServiceImpl implements UsuarioControllerService {
    private static Logger logger = Logger.getLogger(UsuarioController.class.getName());
    private final UsuarioService usuarioService;
    private final LogsService logsService;
    private final RolRepository  rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String ACEPTADO = "Aceptado";
    private static final String ERROR = "Error";

    @Autowired
    public UsuarioControllerServiceImpl(UsuarioService usuarioService, LogsService logsService, RolRepository rolRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.logsService = logsService;
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String insertarUsuario(UsuarioDto usuario, HttpServletRequest request) throws SearchException {
        Rol rol = rolRepository.findById(usuario.getId_rol()).orElseThrow(() -> new SearchException("No se ha encontrado el rol específico"));
        usuarioService.insertarUsuario(new Usuario(usuario,rol,usuario.getContrasenna()));
        logsService.insertarLog(request, ACEPTADO,"Se insertó un usuario");
        return "El usuario se insertó correctamente";
    }

    @Override
    public String modificarUsuario(UsuarioDto usuario,Long id, HttpServletRequest request) throws SearchException {
        Rol rol = rolRepository.findById(usuario.getId()).orElseThrow(() -> new SearchException("No se ha encontrado el rol específico"));
        usuarioService.modificarUsuario(usuario, id,rol);
        logsService.insertarLog(request, ACEPTADO,"Se modificó un usuario");
        return "Usuario Modificado correctamente";
    }

    @Override
    public String eliminarUsuario(Long id, HttpServletRequest request) throws SearchException {
        usuarioService.eliminarUsuario(id);
        logsService.insertarLog(request, ACEPTADO,"Se eliminó un usuario");
        return "Se eliminó el usuario";
    }

    @Override
    public List<UsuarioDtoSent> listarUsuarios(HttpServletRequest request) throws SearchException {
        try{
            return usuarioRepository.findAll().stream().map(UsuarioDtoSent::fromUsuario).toList();
        } finally {
            throw new SearchException("No se puede acceder a la lista de usuarios");
        }
    }

    @Override
    public UsuarioDtoSent obtenerDatosPorId(Long id, HttpServletRequest request) throws SearchException {
        UsuarioDtoSent usuario = UsuarioDtoSent.fromUsuario(usuarioService.obtenerUsuarioXId(id).orElseThrow(() -> new SearchException("No se ha encontrado el usuario especificado")));
        return usuario;
    }

    @Override
    public boolean existeUsuariobynombre(Long id, HttpServletRequest request) throws SearchException {
        boolean exists = usuarioService.existeUsuario(id);
        return exists;
    }
}
