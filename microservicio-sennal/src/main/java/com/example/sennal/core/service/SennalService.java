package com.example.sennal.core.service;

import com.example.sennal.core.dto.SennalDto;
import com.example.sennal.core.entities.Sennal;
import com.example.sennal.core.exception.SearchException;


import java.util.List;

public interface SennalService {
    void insertarsennal(SennalDto sennalDto);

    void eliminarsennal(Long id)throws SearchException;
    List<Sennal> todassennal(Long iddato);
}
