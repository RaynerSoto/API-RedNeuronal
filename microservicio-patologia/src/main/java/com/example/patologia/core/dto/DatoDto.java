package com.example.patologia.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

public record DatoDto(
    Long id,
    LocalDate fecha,
    LocalTime hora,
    int edad,
    boolean patologia,
    double numerodecalzado,
    double medicioncinturatobillo,
    double largopierna,
    double alturadelsensor
) {}
