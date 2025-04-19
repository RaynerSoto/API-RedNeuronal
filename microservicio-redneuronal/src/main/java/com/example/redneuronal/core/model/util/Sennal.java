package com.example.redneuronal.core.model.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sennal {
    private List<Double> accX, accY, accZ;
    private List<Double> gyroX, gyroY, gyroZ;
    private List<Double> magX, magY, magZ;
    private List<String> diagnosticos;

    /*public Sennal(List<Double> accX, List<Double> accY, List<Double> accZ, List<Double> gyroX, List<Double> gyroY,
                  List<Double> gyroZ, List<Double> magX, List<Double> magY, List<Double> magZ, List<String> diagnostico) {
        this.accX = accX;
        this.accY = accY;
        this.accZ = accZ;
        this.gyroX = gyroX;
        this.gyroY = gyroY;
        this.gyroZ = gyroZ;
        this.magX = magX;
        this.magY = magY;
        this.magZ = magZ;
        this.diagnosticos = diagnostico;
    }*/


}
