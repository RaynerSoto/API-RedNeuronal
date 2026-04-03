package com.example.usuario.core.controller;

import com.example.usuario.core.dto.UserName;
import com.example.usuario.core.dto.UsuarioDto;
import com.example.usuario.core.exception.SearchException;
import com.example.usuario.core.service.LogsService;
import com.example.usuario.core.service.UsuarioService;
import com.example.usuario.core.serviceController.UsuarioControllerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/usuario")
@Tag(name = "Controlador de usuarios",
        description = "Controlador encargardo de todo lo referente con los usuarios del sistema")
public class UsuarioController {
    private static Logger logger = Logger.getLogger(UsuarioController.class.getName());
    private final UsuarioService usuarioService;
    private final LogsService logsService;
    private final UsuarioControllerService usuarioControllerService;
    private static final String ACEPTADO = "Aceptado";

    @Autowired
    public UsuarioController(UsuarioService usuarioService, LogsService logsService, UsuarioControllerService usuarioControllerService) {
        this.usuarioService = usuarioService;
        this.logsService = logsService;
        this.usuarioControllerService = usuarioControllerService;
    }

    @PostMapping("/create")
    @Operation(summary = "Insertar usuario",
            description = "Permite insertar un usuario")
    public ResponseEntity<?> insertarUsuario(@RequestBody UsuarioDto usuario, HttpServletRequest request) throws SearchException {
        return ResponseEntity.ok(usuarioControllerService.insertarUsuario(usuario,request));
    }

    @GetMapping("/all")
    @Operation(summary = "Listado de usuarios",
            description = "Permite listar todos los usuarios del sistema")
    public ResponseEntity<?> listarUsuarios(HttpServletRequest request) throws SearchException {
        return ResponseEntity.ok(usuarioControllerService.listarUsuarios(request));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Modificar usuarios por ID",
            description = "Permite modificar un usuario a raíz de su ID")
    public ResponseEntity<?> modificarUsuario(@RequestBody UsuarioDto usuario,@PathVariable Long id, HttpServletRequest request) throws SearchException {
        return ResponseEntity.ok(usuarioControllerService.modificarUsuario(usuario,id,request));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar usuario por ID", description = "Permite eliminar un usuario a raíz de su ID")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id, HttpServletRequest request) throws SearchException {
        return ResponseEntity.ok(usuarioControllerService.eliminarUsuario(id, request));
    }

    @PatchMapping("/obtenerUsuario/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Permite obtener un usuario a raíz de su ID")
    public ResponseEntity<?> obtenerDatosPorId(@PathVariable Long id,HttpServletRequest request) throws SearchException {
        return ResponseEntity.ok(usuarioControllerService.obtenerDatosPorId(id,request));
    }

    @GetMapping("/existeUsuario/{id}")
    public ResponseEntity<?> existeUsuariobynombre(@PathVariable Long id, HttpServletRequest request) throws SearchException {
        return ResponseEntity.ok(usuarioControllerService.existeUsuariobynombre(id,request));
    }

    @GetMapping("/participantesXUsuario/{id}")
    public ResponseEntity<?> obtenerParticipantesXUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerParticipanteXUsuario(id));
    }

    

    @GetMapping("/obtenerUsuarioporNombre/{nombre}")
    @Operation(summary = "Obtener usuario por Nombre", description = "Permite obtener un usuario a raíz de su nombre")
    public ResponseEntity<?> obtenerDatosPorId(@PathVariable String nombre) throws SearchException {
        UserName usuario = new UserName(usuarioService.obtenerUsuarioXNombre(nombre));
        return ResponseEntity.ok(usuario);
    }
}
