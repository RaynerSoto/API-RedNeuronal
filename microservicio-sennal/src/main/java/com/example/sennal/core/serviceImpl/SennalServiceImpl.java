package com.example.sennal.core.serviceImpl;

import com.example.sennal.core.dto.SennalDto;
import com.example.sennal.core.entities.Sennal;
import com.example.sennal.core.repository.SennalRepository;
import com.example.sennal.core.service.SennalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SennalServiceImpl implements SennalService {
    private SennalRepository sennalRepository;
    @Autowired
    public SennalServiceImpl(SennalRepository sennalRepository) {
        this.sennalRepository = sennalRepository;
    }

    @Override
    public void insertarsennal(SennalDto sennalDto) {
        sennalRepository.save(new Sennal(sennalDto));
    }

    @Override
    public void eliminarsennal(Long id) {
        sennalRepository.deleteById(id);
    }

    @Override /* recuerda transformar, que las señales se muestran por datos*/
    public List<Sennal> todassennal() {
        return sennalRepository.findAll();
    }
}
