package com.example.microserviciodato.core.dto;

import com.example.microserviciodato.core.model.DatoParticipante;


public record DatoParticipanteDto(
     Long id,
     Long idparticipante,
     String fecha,
     String hora,
     int edad,
     boolean patologia,
     double numerodecalzado,
     double medicioncinturatobillo,
     double largopierna,
     double alturadelsensor) {

    public static DatoParticipanteDto fromDatoParticipanteDto(DatoParticipante datoParticipante){
        return new DatoParticipanteDto(datoParticipante.getId(),
                datoParticipante.getIdparticipante(),
                datoParticipante.getFecha().toString(),
                datoParticipante.getHora().toString(),
                datoParticipante.getEdad(),
                datoParticipante.isPatologia(),
                datoParticipante.getNumerodecalzado(),
                datoParticipante.getMedicioncinturatobillo(),
                datoParticipante.getLargopierna(),
                datoParticipante.getAlturadelsensor());
    }

    public static DatoParticipanteDto fromDatoParticipanteDtoID(DatoParticipanteDto datoParticipanteDto, Long id){
        return new DatoParticipanteDto(id,
                datoParticipanteDto.idparticipante(),
                datoParticipanteDto.fecha(),
                datoParticipanteDto.hora(),
                datoParticipanteDto.edad(),
                datoParticipanteDto.patologia(),
                datoParticipanteDto.numerodecalzado(),
                datoParticipanteDto.medicioncinturatobillo(),
                datoParticipanteDto.largopierna(),
                datoParticipanteDto.alturadelsensor());
    }
}
