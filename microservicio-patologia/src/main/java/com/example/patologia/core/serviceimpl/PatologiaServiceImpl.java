package com.example.patologia.core.serviceimpl;

import com.example.patologia.core.dto.PatologiaDto;
import com.example.patologia.core.entities.Patologia;
import com.example.patologia.core.exception.SearchException;
import com.example.patologia.core.repository.PatologiaRepository;
import com.example.patologia.core.service.PatologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatologiaServiceImpl implements PatologiaService {
    private PatologiaRepository patologiaRepository;

    @Autowired
    public PatologiaServiceImpl (PatologiaRepository patologiaRepository){
        this.patologiaRepository = patologiaRepository;
    }
    @Override
    public void insertarPatologia(PatologiaDto patologiaDto) {
        patologiaRepository.save(new Patologia(patologiaDto));
    }

    @Override
    public void modificarPatologia(PatologiaDto patologiaDto, Long idpatologia) throws SearchException {
        if(patologiaRepository.existsById(idpatologia)){
            patologiaDto.setId(idpatologia);
            patologiaRepository.save(new Patologia(patologiaDto));
        }else{
            throw new SearchException("No existe la patologia a modificar");
        }
    }

    @Override
    public void eliminarPatologia(Long idpatologia) throws SearchException {
        if(patologiaRepository.existsById(idpatologia)){
            patologiaRepository.deleteById(idpatologia);
        }else{
            throw new SearchException("No existe la patologia a eliminar");
        }
    }

    @Override
    public List<Patologia> obtenerPatologias() {
        return patologiaRepository.findAll();
    }
}
