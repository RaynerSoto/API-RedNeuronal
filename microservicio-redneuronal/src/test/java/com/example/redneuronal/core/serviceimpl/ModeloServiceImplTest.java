package com.example.redneuronal.core.serviceimpl;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
class ModeloServiceImplTest {

    Logger logger = Logger.getLogger(ModeloServiceImplTest.class.getName());

    @Test
    public void arregloTest(){
        logger.info("Iniciando la prueba del arreglo de características...");
        double[] arreglo = new double[45];
        logger.info(Arrays.toString(arreglo));
        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = i * 0.1;
        }
        logger.info("Arreglo de características: " + java.util.Arrays.toString(arreglo));

    }

}