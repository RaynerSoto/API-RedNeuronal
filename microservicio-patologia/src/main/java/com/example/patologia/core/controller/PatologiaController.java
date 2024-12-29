package com.example.patologia.core.controller;

import com.example.patologia.core.dto.PatologiaDto;
import com.example.patologia.core.service.LogsService;
import com.example.patologia.core.service.PatologiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patologia")
@Tag(name = "Controlador de patologias",
        description = "Controlador encargardo de todo lo referente con las patologias")
public class PatologiaController {
    private PatologiaService patologiaService;
    private LogsService logsService;
    private String aceptado = "Aceptado";
    private String error = "Error";

    @Autowired
    public PatologiaController(PatologiaService patologiaService, LogsService logsService){
        this.patologiaService = patologiaService;
        this.logsService = logsService;
    }

    @PostMapping("/create")
    @Operation(summary = "Insertar patologia",
            description = "Permite insertar una patologia")
    public ResponseEntity<?> insertarPatologia(@RequestBody PatologiaDto patologiaDto, HttpServletRequest request){
        try{
            patologiaService.insertarPatologia(patologiaDto);
            logsService.insertarLog(request,aceptado,"Se insertó una patologia");
            return ResponseEntity.ok("Se insertó una patologia correctamente");
        }catch (Exception e){
            logsService.insertarLog(request,error,e.getMessage());
            return ResponseEntity.badRequest().body("Ocurrio un error");
        }
    }

    @PutMapping("/update/{idpatologia}")
    @Operation(summary = "Modificar patologia",
            description = "Permite modificar una patologia")
    public ResponseEntity<?> modificarPatologia(@RequestBody PatologiaDto patologiaDto,@PathVariable Long idpatologia,HttpServletRequest request){
        try{
            patologiaService.modificarPatologia(patologiaDto,idpatologia);
            logsService.insertarLog(request,aceptado,"Se modificó un usuario");
            return ResponseEntity.ok("Se ha modificado correctamente");
        }catch (Exception e){
            logsService.insertarLog(request,error,e.getMessage());
            return ResponseEntity.badRequest().body("Ocurrio un error");
        }
    }

    @DeleteMapping("/delete/{idpatologia}")
    @Operation(summary = "Eliminar patologia",
            description = "Permite eliminar una patologia")
    public ResponseEntity<?> eliminarPatologia(@PathVariable Long idpatologia,HttpServletRequest request){
        try{
            patologiaService.eliminarPatologia(idpatologia);
            logsService.insertarLog(request,aceptado,"Se elimonó una patologia");
            return ResponseEntity.ok("Se eliminó una patologia correctamente");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error");
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todas las patologias",
            description = "Permite obtener todas las patologias")
    public ResponseEntity<?> obtenerPatologias(){
        try{
            return ResponseEntity.ok(patologiaService.obtenerPatologias());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
