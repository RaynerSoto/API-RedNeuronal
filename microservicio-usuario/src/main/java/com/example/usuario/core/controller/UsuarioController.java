package com.example.usuario.core.controller;

import com.example.usuario.core.dto.UsuarioDTO;
import com.example.usuario.core.entities.Usuario;
import com.example.usuario.core.service.LogsService;
import com.example.usuario.core.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Controlador de usuarios",
        description = "Controlador encargardo de todo lo referente con los usuarios del sistema")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final LogsService logsService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService,LogsService logsService) {
        this.usuarioService = usuarioService;
        this.logsService = logsService;
    }

    @PostMapping("/create")
    @Operation(summary = "Insertar usuario",
            description = "Permite insertar un usuario")
    public ResponseEntity<?> insertarUsuario(@RequestBody UsuarioDTO usuario, HttpServletRequest request){
        try{
            usuarioService.insertarUsuario(new Usuario(usuario));
            logsService.insertarLog(request,"Aceptado","Se insertó un usuario");
            return ResponseEntity.ok("El usuario se insertó correctamente");
        }catch (Exception e){
            logsService.insertarLog(request,"Error",e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Listado de empleados",
            description = "Permite listar todos los empleados del sistema junto con sus centros laborales")
    public ResponseEntity<?> listarUsuarios(){
       try{
           List<Usuario> usuarios = usuarioService.obtenerUsuarios();
           return ResponseEntity.ok(usuarios);
       }catch (Exception e){
           return ResponseEntity.badRequest().body("No se puede acceder a la lista de usuarios");
       }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Modificar usuarios por ID",
            description = "Permite modificar un usuario a raíz de su ID")
    public ResponseEntity<?> modificarUsuario(@RequestBody UsuarioDTO usuario,HttpServletRequest request){
        try{
            usuarioService.modificarUsuario(new Usuario(usuario));
            logsService.insertarLog(request,"Aceptado","Se modificó un usuario");
            return ResponseEntity.ok("Usuario Modificado correctamente");
        }catch (Exception e){
            logsService.insertarLog(request,"Error",e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar usuario por ID", description = "Permite eliminar un usuario a raíz de su ID")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id, HttpServletRequest request){
        try{
            usuarioService.eliminarUsuario(id);
            logsService.insertarLog(request,"Aceptado","Se eliminó un usuario");
            return ResponseEntity.ok("Se eliminó el usuario");
        }catch (Exception e){
            logsService.insertarLog(request,"Error",e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/obtenerUsuario/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Permite obtener un usuario a raíz de su ID")
    public ResponseEntity<?> obtenerDatosPorId(@PathVariable Long id){
        try{
            UsuarioDTO usuario = new UsuarioDTO(usuarioService.obtenerUsuarioXId(id).get());
            return ResponseEntity.ok(usuario);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
