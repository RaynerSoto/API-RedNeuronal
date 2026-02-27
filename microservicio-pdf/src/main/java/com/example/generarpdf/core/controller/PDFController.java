package com.example.generarpdf.core.controller;

import com.example.generarpdf.core.service.ParticipantesUsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/generarpdf")
@Tag(name = "Controlador de pdf",
        description = "Controlador encargardo de generar PDF")
public class PDFController {

    private final ParticipantesUsuarioService participantesUsuario;

    @Autowired
    public PDFController(ParticipantesUsuarioService participantesUsuario) {
        this.participantesUsuario = participantesUsuario;
    }

    // Endpoint para generar el PDF del participante
    @GetMapping("generar-pdf/{nombre}")
    public ResponseEntity<?> generarPdf(@PathVariable String nombre, HttpServletResponse response) throws Exception {
            try {
/*
                byte[] pdfBytes = participantesUsuario.generarPdf(nombre);;

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "participantes.pdf");

                return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
                */
                Map<String, String> pdfsBase64 = participantesUsuario.generarPdf(nombre);
                return ResponseEntity.ok(pdfsBase64);


            } catch (Exception e){

                return ResponseEntity.badRequest().body(e + "\n" + participantesUsuario.obtenerparticipantes(nombre).toString());
            }
    }

    @GetMapping("/prueba/{nombre}")
    public ResponseEntity<?> probar(@PathVariable String nombre, HttpServletResponse response) throws Exception {
        try{

            return ResponseEntity.ok(participantesUsuario.obtenerparticipantes(nombre));
        } catch (Exception e){

            return ResponseEntity.badRequest().body(e + "\n" + participantesUsuario.obtenerparticipantes(nombre).toString());
        }
    }
}
