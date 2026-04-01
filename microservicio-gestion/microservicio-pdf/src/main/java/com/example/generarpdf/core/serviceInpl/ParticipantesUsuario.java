package com.example.generarpdf.core.serviceInpl;

import com.example.generarpdf.core.dto.*;
import com.example.generarpdf.core.dto.predecir.ModeloRequest;
import com.example.generarpdf.core.dto.predecir.Sennal;
import com.example.generarpdf.core.feignClient.ParticipanteClient;
import com.example.generarpdf.core.feignClient.PatologiasClient;
import com.example.generarpdf.core.feignClient.RedNeuronalClient;
import com.example.generarpdf.core.feignClient.UsuarioParticipanteClient;
import com.example.generarpdf.core.http.response.ParticipanteResponse;
import com.example.generarpdf.core.service.ParticipantesUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantesUsuario implements ParticipantesUsuarioService {
    private UsuarioParticipanteClient usuarioParticipanteClient;
    private ParticipanteClient participanteClient;
    private PatologiasClient patologiasClient;
    private RedNeuronalClient redNeuronalClient;
    private List<ParticipanteDto> participanteList;

    List<DatoDto> datoList;

    @Autowired
    public ParticipantesUsuario(UsuarioParticipanteClient usuarioParticipanteClient,
                                ParticipanteClient participanteClient,
                                PatologiasClient patologiasClient,
                                RedNeuronalClient redNeuronalClient) {
        this.usuarioParticipanteClient = usuarioParticipanteClient;
        this.participanteClient = participanteClient;
        this.participanteList = new ArrayList<>();
        this.patologiasClient = patologiasClient;
        this.redNeuronalClient = redNeuronalClient;
    }


    @Override
    public ParticipanteResponse obtenerparticipantes(String nombre) {
        UserName usuario = usuarioParticipanteClient.existeUsuariobynombre(nombre);
        List<ParticipantesDto> participantesDtoList = participanteClient.obtenerParticipanteXUsuario(usuario.getId());
        List<ParticipanteDto> participanteList = new ArrayList<>();

        // Obtener todas las patologías una sola vez, fuera del bucle
        List<PatologiaDto> patologiaDtoList = patologiasClient.obtenerPatologias();
        List<String> nombresPatologias = patologiaDtoList.stream()
                .map(PatologiaDto::getNombre)
                .collect(Collectors.toList());

        for (ParticipantesDto p : participantesDtoList) {
            Long idParticipante = participanteClient.existeParticipantebyCi(p.getCi());
            ParticipanteDto participanteDto = participanteClient.obtenerDatoXParticipante(idParticipante);

            for (DatoDto datoDto : participanteDto.getDatoDtoList()) {
                if (datoDto.getPatologiaList().isEmpty()) {
                    // Crear una sola señal a partir de los datos
                    Sennal senal = new Sennal(
                            datoDto.getSennalList().getAcelerometroX(),
                            datoDto.getSennalList().getAcelerometroY(),
                            datoDto.getSennalList().getAcelerometroZ(),
                            datoDto.getSennalList().getGiroscopioX(),
                            datoDto.getSennalList().getGiroscopioY(),
                            datoDto.getSennalList().getGiroscopioZ(),
                            datoDto.getSennalList().getMagnetometroX(),
                            datoDto.getSennalList().getMagnetometroY(),
                            datoDto.getSennalList().getMagnetometroZ(),
                            null
                    );

                    ModeloRequest modeloRequest = new ModeloRequest(Collections.singletonList(senal), nombresPatologias);
                    List<String> predicciones = redNeuronalClient.predecir(modeloRequest);

                    for (String prediccion : predicciones) {
                        datoDto.getPatologiaList().add(new PatologiaDto(prediccion));
                    }
                }
            }

            participanteList.add(participanteDto);
        }

        return ParticipanteResponse.builder()
                .participantesList(participanteList)
                .build();
    }


    @Override
    // Método para generar el PDF
    public byte[] generarPdf(String nombre) throws Exception {
        UserName usuario = usuarioParticipanteClient.existeUsuariobynombre(nombre);
        List<ParticipantesDto> participantesDtoList = participanteClient.obtenerParticipanteXUsuario(usuario.getId());
        List<ParticipanteDto> participanteList = new ArrayList<>();

        // Obtener todas las patologías una sola vez, fuera del bucle
        List<PatologiaDto> patologiaDtoList = patologiasClient.obtenerPatologias();
        List<String> nombresPatologias = patologiaDtoList.stream()
                .map(PatologiaDto::getNombre)
                .collect(Collectors.toList());

        for (ParticipantesDto p : participantesDtoList) {
            Long idParticipante = participanteClient.existeParticipantebyCi(p.getCi());
            ParticipanteDto participanteDto = participanteClient.obtenerDatoXParticipante(idParticipante);

            for (DatoDto datoDto : participanteDto.getDatoDtoList()) {
                if (datoDto.getPatologiaList().isEmpty()) {
                    // Crear una sola señal a partir de los datos
                    Sennal senal = new Sennal(
                            datoDto.getSennalList().getAcelerometroX(),
                            datoDto.getSennalList().getAcelerometroY(),
                            datoDto.getSennalList().getAcelerometroZ(),
                            datoDto.getSennalList().getGiroscopioX(),
                            datoDto.getSennalList().getGiroscopioY(),
                            datoDto.getSennalList().getGiroscopioZ(),
                            datoDto.getSennalList().getMagnetometroX(),
                            datoDto.getSennalList().getMagnetometroY(),
                            datoDto.getSennalList().getMagnetometroZ(),
                            null
                    );

                    ModeloRequest modeloRequest = new ModeloRequest(Collections.singletonList(senal), nombresPatologias);
                    List<String> predicciones = redNeuronalClient.predecir(modeloRequest);

                    for (String prediccion : predicciones) {
                        datoDto.getPatologiaList().add(new PatologiaDto(prediccion));
                    }
                }
            }

            participanteList.add(participanteDto);
        }
        return CrearPDF.generateParticipantPdf(participanteList, usuario);
    }

    @Override
    public List<PatologiaDto> obtenerpatologias() {
        return patologiasClient.obtenerPatologias();
    }

}
