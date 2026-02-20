package com.example.microserviciodato.core.model;

import com.example.microserviciodato.core.dto.DatoParticipanteDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="datoparticipante")
@Builder
public class DatoParticipante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_participante")
    private Long idparticipante;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "hora")
    private LocalTime hora;
    @Column(name = "edad")
    private int edad;
    @Column(name = "patologia")
    private boolean patologia;
    @Column(name = "numerodecalzado")
    private double numerodecalzado;
    @Column(name = "medicioncinturatobillo")
    private double medicioncinturatobillo;
    @Column(name = "largopierna")
    private double largopierna;
    @Column(name = "alturadelsensor")
    private double alturadelsensor;

    public DatoParticipante(DatoParticipanteDto datoParticipanteDto) {
        this.idparticipante = datoParticipanteDto.idparticipante();
        this.fecha = LocalDate.parse(datoParticipanteDto.fecha());
        this.hora = LocalTime.parse(datoParticipanteDto.hora());
        this.edad = datoParticipanteDto.edad();
        this.patologia = datoParticipanteDto.patologia();
        this.numerodecalzado = datoParticipanteDto.numerodecalzado();
        this.medicioncinturatobillo = datoParticipanteDto.medicioncinturatobillo();
        this.largopierna = datoParticipanteDto.largopierna();
        this.alturadelsensor = datoParticipanteDto.alturadelsensor();
    }
}
