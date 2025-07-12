package com.example.generarpdf.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipanteDto {
    private String nombre;

    private String ci;

    private String sexo;

    private String telefono;
    private List<DatoDto> datoDtoList;
}
