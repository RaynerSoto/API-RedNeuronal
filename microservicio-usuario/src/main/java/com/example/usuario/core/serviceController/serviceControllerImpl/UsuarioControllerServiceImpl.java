package com.example.usuario.core.serviceController.serviceControllerImpl;

import com.example.usuario.core.controller.UsuarioController;
import com.example.usuario.core.dto.UsuarioDto;
import com.example.usuario.core.exception.SearchException;
import com.example.usuario.core.model.Rol;
import com.example.usuario.core.model.Usuario;
import com.example.usuario.core.repository.RolRepository;
import com.example.usuario.core.service.LogsService;
import com.example.usuario.core.service.UsuarioService;
import com.example.usuario.core.serviceController.UsuarioControllerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UsuarioControllerServiceImpl implements UsuarioControllerService {
    private static Logger logger = Logger.getLogger(UsuarioController.class.getName());
    private final UsuarioService usuarioService;
    private final LogsService logsService;
    private final RolRepository  rolRepository;
    private static final String ACEPTADO = "Aceptado";
    private static final String ERROR = "Error";

    @Autowired
    public UsuarioControllerServiceImpl(UsuarioService usuarioService, LogsService logsService, RolRepository rolRepository) {
        this.usuarioService = usuarioService;
        this.logsService = logsService;
        this.rolRepository = rolRepository;
    }

    @Override
    public String insertarUsuario(UsuarioDto usuario, HttpServletRequest request) throws SearchException {
        Rol rol = rolRepository.findById(usuario.getId()).orElseThrow(() -> new SearchException("No se ha encontrado el rol específico"));
        usuarioService.insertarUsuario(new Usuario(usuario,rol));
        logsService.insertarLog(request, ACEPTADO,"Se insertó un usuario");
        return "El usuario se insertó correctamente";
    }

    @Override
    public String modificarUsuario(UsuarioDto usuarioDto, HttpServletRequest request) throws SearchException {
        return "";
    }
}
