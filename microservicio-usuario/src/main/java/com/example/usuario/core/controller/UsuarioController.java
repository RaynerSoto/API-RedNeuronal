package com.example.usuario.core.controller;

import com.example.usuario.core.dto.UserName;
import com.example.usuario.core.dto.UsuarioDto;
import com.example.usuario.core.entities.Usuario;
import com.example.usuario.core.exception.SearchException;
import com.example.usuario.core.service.LogsService;
import com.example.usuario.core.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/usuario")
@Tag(name = "Controlador de usuarios",
        description = "Controlador encargardo de todo lo referente con los usuarios del sistema")
public class UsuarioController {
    private static Logger logger = Logger.getLogger(UsuarioController.class.getName());
    private final UsuarioService usuarioService;
    private final LogsService logsService;
    private static final String ACEPTADO = "Aceptado";
    private static final String ERROR = "Error";

    @Autowired
    public UsuarioController(UsuarioService usuarioService,LogsService logsService) {
        this.usuarioService = usuarioService;
        this.logsService = logsService;
    }

    @PostMapping("/create")
    @Operation(summary = "Insertar usuario",
            description = "Permite insertar un usuario")
    public ResponseEntity<?> insertarUsuario(@RequestBody UsuarioDto usuario, HttpServletRequest request){
        usuarioService.insertarUsuario(new Usuario(usuario));
        logsService.insertarLog(request, ACEPTADO,"Se insertó un usuario");
        return ResponseEntity.ok("El usuario se insertó correctamente");
    }

    @GetMapping("/all")
    @Operation(summary = "Listado de usuarios",
            description = "Permite listar todos los usuarios del sistema")
    public ResponseEntity<?> listarUsuarios() throws SearchException {
       try{
           List<Usuario> usuarios = usuarioService.obtenerUsuarios();
           return ResponseEntity.ok(usuarios);
       }finally{
           throw new SearchException("No se puede acceder a la lista de usuarios");
       }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Modificar usuarios por ID",
            description = "Permite modificar un usuario a raíz de su ID")
    public ResponseEntity<?> modificarUsuario(@RequestBody UsuarioDto usuario,@PathVariable Long id, HttpServletRequest request) throws SearchException {
        usuarioService.modificarUsuario(usuario, id);
        logsService.insertarLog(request, ACEPTADO,"Se modificó un usuario");
        return ResponseEntity.ok("Usuario Modificado correctamente");
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar usuario por ID", description = "Permite eliminar un usuario a raíz de su ID")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id, HttpServletRequest request) throws SearchException {
        usuarioService.eliminarUsuario(id);
        logsService.insertarLog(request, ACEPTADO,"Se eliminó un usuario");
        return ResponseEntity.ok("Se eliminó el usuario");
    }

    @PatchMapping("/obtenerUsuario/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Permite obtener un usuario a raíz de su ID")
    public ResponseEntity<?> obtenerDatosPorId(@PathVariable Long id) throws SearchException {
        UsuarioDto usuario = new UsuarioDto(usuarioService.obtenerUsuarioXId(id).get());
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/existeUsuario/{id}")
    public ResponseEntity<?> existeUsuariobynombre(@PathVariable Long id) throws SearchException {
        boolean exists = usuarioService.existeUsuario(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/participantesXUsuario/{id}")
    public ResponseEntity<?> obtenerParticipantesXUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerParticipanteXUsuario(id));
    }

    @GetMapping("/existeUsuariobynombre/{nombre}")
    public ResponseEntity<?> existeUsuariobynombre(@PathVariable String nombre) throws SearchException {
        Long id = usuarioService.existeUsuariobynombre(nombre);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/obtenerUsuarioporNombre/{nombre}")
    @Operation(summary = "Obtener usuario por Nombre", description = "Permite obtener un usuario a raíz de su nombre")
    public ResponseEntity<?> obtenerDatosPorId(@PathVariable String nombre) throws SearchException {
        UserName usuario = new UserName(usuarioService.obtenerUsuarioXNombre(nombre));
        return ResponseEntity.ok(usuario);
    }

}
