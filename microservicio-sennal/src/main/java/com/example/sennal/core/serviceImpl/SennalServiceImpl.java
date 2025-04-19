package com.example.sennal.core.serviceImpl;

import com.example.sennal.core.dto.SennalDto;
import com.example.sennal.core.entities.Sennal;
import com.example.sennal.core.exception.SearchException;
import com.example.sennal.core.feingclient.DatoClient;
import com.example.sennal.core.repository.SennalRepository;
import com.example.sennal.core.service.SennalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SennalServiceImpl implements SennalService {
    private SennalRepository sennalRepository;
    private DatoClient datoClient;
    @Autowired
    public SennalServiceImpl(SennalRepository sennalRepository, DatoClient datoClient) {
        this.sennalRepository = sennalRepository;
        this.datoClient = datoClient;
    }

    @Override
    public void insertarsennal(SennalDto sennalDto) {
        if(!sennalRepository.existsByIddatoAndFecha(sennalDto.getIddato(), LocalDate.parse(sennalDto.getFecha()))){
            sennalRepository.save(new Sennal(sennalDto));
        }
    }

    @Override
    public void eliminarsennal(Long id) throws SearchException {
        if (sennalRepository.existsById(id)){
            sennalRepository.deleteById(id);
        }else{
            throw new SearchException("No existe la sennal a eliminar");
        }
    }

    @Override
    public List<Sennal> todassennal(Long iddato) {
        List<Sennal> sennalList = null;
        if(datoClient.existeDato(iddato)){
            sennalList = sennalRepository.findAllByIddato(iddato);
        }
        return sennalList;
    }
}
