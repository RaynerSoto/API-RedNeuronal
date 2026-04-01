package com.example.generarpdf.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserName {
    private Long id;
    private Long id_rol;
    private String nombre;
}
