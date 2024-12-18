package com.example.usuario.core.dto;

import com.example.usuario.core.entities.Usuario;

public class UsuarioDto {
    private Long identificador;
    private Long id_rol;
    private String nombre;
    private String contrasenna;
    private String repetircontrasenna;
    private String noprofesional;

    public UsuarioDto(Long identificador, Long id_rol, String nombre, String contrasenna, String repetircontrasenna, String noprofesional) {
        this.identificador = identificador;
        this.id_rol = id_rol;
        this.nombre = nombre;
        this.contrasenna = contrasenna;
        this.repetircontrasenna = repetircontrasenna;
        this.noprofesional = noprofesional;
    }

    public UsuarioDto(Usuario usuario) {
        this.identificador = usuario.getId();
        this.id_rol = usuario.getId_rol();
        this.nombre = usuario.getNombre();
        this.contrasenna = usuario.getContrasenna();
        this.repetircontrasenna = usuario.getRepetrircontrasenna();
        this.noprofesional = usuario.getNoprofesional();
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getRepetircontrasenna() {
        return repetircontrasenna;
    }

    public void setRepetircontrasenna(String repetircontrasenna) {
        this.repetircontrasenna = repetircontrasenna;
    }

    public String getNoprofesional() {
        return noprofesional;
    }

    public void setNoprofesional(String noprofesional) {
        this.noprofesional = noprofesional;
    }
}
