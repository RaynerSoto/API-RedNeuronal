package com.example.usuario.core.dto;

import com.example.usuario.core.model.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDto {
    private Long id;
    private Long id_rol;
    private String nombre;
    private String contrasenna;
    private String noprofesional;

    public UsuarioDto(Long id, Long id_rol, String nombre, String contrasenna, String noprofesional) {
        this.id = id;
        this.id_rol = id_rol;
        this.nombre = nombre;
        this.contrasenna = contrasenna;
        this.noprofesional = noprofesional;
    }

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.id_rol = usuario.getIdrol().getId();
        this.nombre = usuario.getNombre();
        this.contrasenna = usuario.getContrasenna();
        this.noprofesional = usuario.getNoprofesional();
    }
}
