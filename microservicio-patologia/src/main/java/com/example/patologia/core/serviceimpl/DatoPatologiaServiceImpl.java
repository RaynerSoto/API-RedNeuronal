package com.example.patologia.core.serviceimpl;

import com.example.patologia.core.dto.DatoPatologiaDto;
import com.example.patologia.core.entities.DatoPatologia;
import com.example.patologia.core.repository.DatoPatologisRepository;
import com.example.patologia.core.service.DatoPatologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatoPatologiaServiceImpl implements DatoPatologiaService {
    private DatoPatologisRepository datoPatologisRepository;

    @Autowired
    public DatoPatologiaServiceImpl(DatoPatologisRepository datoPatologisRepository) {
        this.datoPatologisRepository = datoPatologisRepository;
    }

    @Override
    public void crearRelacion(DatoPatologia datoPatologia) {
        datoPatologisRepository.save(datoPatologia);
    }
}
