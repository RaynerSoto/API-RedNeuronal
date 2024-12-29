package com.example.microserviciodato.core.serviceimpl;

import com.example.microserviciodato.core.dto.DatoParticipanteDto;
import com.example.microserviciodato.core.entities.DatoParticipante;
import com.example.microserviciodato.core.exception.SearchException;
import com.example.microserviciodato.core.repository.DatoParticipanteRepository;
import com.example.microserviciodato.core.service.DatoParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DatoParticipanteServiceImpl implements DatoParticipanteService {
    private DatoParticipanteRepository datoParticipanteRepository;

    @Autowired
    public DatoParticipanteServiceImpl(DatoParticipanteRepository datoParticipanteRepository) {
        this.datoParticipanteRepository = datoParticipanteRepository;
    }

    @Override
    public void insertarDato(DatoParticipante datoParticipante) {
        datoParticipanteRepository.save(datoParticipante);
    }

    @Override
    public void modificarDato(DatoParticipanteDto datoParticipante, Long id) {
        if(datoParticipanteRepository.existsById(id)){
            datoParticipante.setId(id);
      datoParticipanteRepository.save(new DatoParticipante(datoParticipante));
        }
    }

    @Override
    public void eliminarDato(Long id) {

    }

    @Override
    public List<DatoParticipante> obtenerDatosXParticipante(Long id) {
        return datoParticipanteRepository.findAllByIdparticipante(id);
    }

    @Override
    public Optional<DatoParticipante> obtenerDatoXId(Long id) throws SearchException {
        return Optional.empty();
    }

    @Override
    public boolean existeDato(Long id) throws SearchException {
        return false;
    }
}
