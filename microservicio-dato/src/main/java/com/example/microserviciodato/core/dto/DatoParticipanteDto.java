package com.example.microserviciodato.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatoParticipanteDto {
    private Long id;
    private Long idparticipante;
    private LocalDate fecha;
    private LocalTime hora;
    private int edad;
    private boolean patologia;
    private double numerodecalzado;
    private double medicioncinturatobillo;
    private double largopierna;
    private double alturadelsensor;
}
