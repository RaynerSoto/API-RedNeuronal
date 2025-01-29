package com.example.sennal.core.service;

import com.example.sennal.core.dto.SennalDto;
import com.example.sennal.core.entities.Sennal;

import java.util.List;

public interface SennalService {
    void insertarsennal(SennalDto sennalDto);

    void eliminarsennal(Long id);
    List<Sennal> todassennal();
}
