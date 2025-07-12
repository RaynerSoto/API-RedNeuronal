package com.example.usuario.core.dto;

import com.example.usuario.core.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserName {
    private Long id;
    private Long id_rol;
    private String nombre;

    public UserName(Usuario usuario) {
        this.id = usuario.getId();
        this.id_rol = usuario.getIdrol();
        this.nombre = usuario.getNombre();
    }
}
