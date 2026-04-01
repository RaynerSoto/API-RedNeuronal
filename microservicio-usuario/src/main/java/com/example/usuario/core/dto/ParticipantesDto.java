package com.example.usuario.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantesDto {

    private String nombre;

    private String ci;

    private String sexo;

    private String telefono;

}
