package com.example.generarpdf.core.feignClient;

import com.example.generarpdf.core.dto.predecir.ModeloRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="microservicio-redneuronal", url = "localhost:8090/api/v1/modelo")
public interface RedNeuronalClient  {
    @PostMapping("/predecir")
    List<String> predecir(@RequestBody ModeloRequest request);
}
