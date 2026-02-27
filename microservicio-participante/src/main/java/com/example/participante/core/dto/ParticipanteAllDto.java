package com.example.participante.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
public record ParticipanteAllDto(
        String nombre,
        String ci,
        String sexo,
        String telefono,
        List<DatoDto> datoDtoList
) {}
