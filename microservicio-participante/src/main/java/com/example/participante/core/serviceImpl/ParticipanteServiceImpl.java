package com.example.participante.core.serviceImpl;

import com.example.participante.core.entities.Participante;
import com.example.participante.core.exception.SearchException;
import com.example.participante.core.repository.ParticipanteRepository;
import com.example.participante.core.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteServiceImpl implements ParticipanteService {
    private ParticipanteRepository participanteRepository;

    @Autowired
    public ParticipanteServiceImpl(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    @Override
    public void insertarParticipante(Participante participante) {
        participanteRepository.save(participante);
    }

    @Override
    public void modificarParticipante(Participante participante) throws SearchException {
        if(participanteRepository.existsById(participante.getId())){
            participanteRepository.save(participante);
        }else{
            throw new SearchException("No existe el participante a modificar");
        }
    }

    @Override
    public void eliminarParticipante(Long id) throws SearchException {
        if(participanteRepository.existsById(id)){
            participanteRepository.deleteById(id);
        }else{
            throw new SearchException("No existe el participante a eliminar");
        }
    }

    @Override
    public List<Participante> obtenerParticipantes() {
        return participanteRepository.findAll();
    }

    @Override
    public Optional<Participante> obtenerParticipanteXId(Long id) throws SearchException {
        return Optional.ofNullable(participanteRepository.findById(id).orElseThrow(()-> new SearchException("No existe un participante con ese ID")));
    }

    @Override
    public Optional<Participante> obtenerParticipanteXCI(String ci) throws SearchException {
        return Optional.ofNullable(participanteRepository.findByCi(ci).orElseThrow(()-> new SearchException("No existe un participante con ese ID")));
    }
}
