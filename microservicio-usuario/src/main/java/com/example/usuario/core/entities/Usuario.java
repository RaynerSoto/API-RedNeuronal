package com.example.usuario.core.entities;

import com.example.usuario.core.dto.UsuarioDto;
import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="id_rol")
    private Long id_rol;
    @Column(name = "nombre")
    private String nombre;
    @Column(name ="contrasenna")
    private String contrasenna;
    @Column(name ="repetircontrasenna")
    private String repetrircontrasenna;
    @Column(name ="noprofesional", unique = true)
    private String noprofesional;

    public Usuario(UsuarioDto usuario) {
        this.id = usuario.getIdentificador();
        this.id_rol = usuario.getId_rol();
        this.nombre = usuario.getNombre();
        this.contrasenna = usuario.getContrasenna();
        this.repetrircontrasenna = usuario.getRepetircontrasenna();
        this.noprofesional = usuario.getNoprofesional();
    }
}
