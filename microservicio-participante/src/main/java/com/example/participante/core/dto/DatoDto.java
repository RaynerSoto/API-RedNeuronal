package com.example.participante.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record DatoDto (LocalDate fecha, LocalTime hora, int edad, boolean patologia,
    List<PatologiaDto> patologiaList,
    double numerodecalzado,
    double medicioncinturatobillo,
    double largopierna,
    double alturadelsensor,
    SennalDto sennalList
) {}