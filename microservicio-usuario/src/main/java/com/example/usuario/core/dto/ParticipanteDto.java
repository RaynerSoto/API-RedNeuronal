package com.example.usuario.core.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipanteDto {

    private String nombre;

    private String ci;

    private String sexo;

    private String telefono;
}
