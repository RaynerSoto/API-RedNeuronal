package com.example.sennal.core.serviceImpl;

import com.example.sennal.core.dto.ListaSensoresDto;
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
import java.util.stream.Collectors;

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
        sennalRepository.save(new Sennal(sennalDto));

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
    public ListaSensoresDto todassennal(Long iddato) {
        ListaSensoresDto listaSensoresDto = new ListaSensoresDto();

        if (datoClient.existeDato(iddato)) {
            List<Sennal> sennalList = sennalRepository.findAllByIddato(iddato);

            listaSensoresDto.setAcelerometroX(
                    sennalList.stream().map(Sennal::getAcelerometrox).collect(Collectors.toList())
            );
            listaSensoresDto.setAcelerometroY(
                    sennalList.stream().map(Sennal::getAcelerometroy).collect(Collectors.toList())
            );
            listaSensoresDto.setAcelerometroZ(
                    sennalList.stream().map(Sennal::getAcelerometroz).collect(Collectors.toList())
            );

            listaSensoresDto.setGiroscopioX(
                    sennalList.stream().map(Sennal::getGiroscopiox).collect(Collectors.toList())
            );
            listaSensoresDto.setGiroscopioY(
                    sennalList.stream().map(Sennal::getGiroscopioy).collect(Collectors.toList())
            );
            listaSensoresDto.setGiroscopioZ(
                    sennalList.stream().map(Sennal::getGiroscopioz).collect(Collectors.toList())
            );

            listaSensoresDto.setMagnetometroX(
                    sennalList.stream().map(Sennal::getMagnetometrox).collect(Collectors.toList())
            );
            listaSensoresDto.setMagnetometroY(
                    sennalList.stream().map(Sennal::getMagnetometroy).collect(Collectors.toList())
            );
            listaSensoresDto.setMagnetometroZ(
                    sennalList.stream().map(Sennal::getMagnetometroz).collect(Collectors.toList())
            );
        }

        return listaSensoresDto;
    }
}
