package com.example.microserviciodato.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public record DatoIdDto (
    Long idparticipante,
    String fecha){}
