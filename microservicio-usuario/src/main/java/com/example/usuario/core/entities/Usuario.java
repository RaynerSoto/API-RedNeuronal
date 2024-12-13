package com.example.usuario.core.entities;

import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name ="contrasenna")
    private String contrasenna;
    @Column(name ="repetrircontrasenna")
    private String repetrircontrasenna;
    @Column(name ="noprodesional")
    private String noprodesional;
    /*@ManyToMany
    @JoinTable(
            name = "usuario_participante", // Nombre de la tabla intermedia existente
            joinColumns = @JoinColumn(name = "id_usuario"), // Columna que referencia a Usuario
            inverseJoinColumns = @JoinColumn(name = "id_participante") // Columna que referencia a Participante)
            private List<Empleado> personal = new ArrayList<>();*/

    public Usuario(String nombre, String contrasenna, String repetrircontrasenna, String noprodesional) {
        this.nombre = nombre;
        this.contrasenna = contrasenna;
        this.repetrircontrasenna = repetrircontrasenna;
        this.noprodesional = noprodesional;
    }
}
