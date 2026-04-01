package com.example.generarpdf.core.dto.predecir;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModeloRequest {
    public List<Sennal> sennalList;
    public List<String> enfermedades;
}
