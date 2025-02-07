package com.example.microserviciodato.core.dto;

import com.example.microserviciodato.core.entities.DatoParticipante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatoParticipanteDto {
    private Long id;
    private Long idparticipante;
    private LocalDate fecha;
    private LocalTime hora;
    private int edad;
    private boolean patologia;
    private double numerodecalzado;
    private double medicioncinturatobillo;
    private double largopierna;
    private double alturadelsensor;

    public DatoParticipanteDto(DatoParticipante datoParticipante) {
        this.id = datoParticipante.getId();
        this.idparticipante = datoParticipante.getIdparticipante();
        this.fecha = datoParticipante.getFecha();
        this.hora = datoParticipante.getHora();
        this.edad = datoParticipante.getEdad();
        this.patologia = datoParticipante.isPatologia();
        this.numerodecalzado = datoParticipante.getNumerodecalzado();
        this.medicioncinturatobillo = datoParticipante.getMedicioncinturatobillo();
        this.largopierna = datoParticipante.getLargopierna();
        this.alturadelsensor = datoParticipante.getAlturadelsensor();
    }
}
