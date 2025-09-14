package com.example.usuario.core.service.serviceimpl;

import com.example.usuario.core.dto.ParticipantesDto;
import com.example.usuario.core.dto.UsuarioDto;
import com.example.usuario.core.model.Usuario;
import com.example.usuario.core.exception.SearchException;
import com.example.usuario.core.feignClient.ParticipanteClient;
import com.example.usuario.core.http.response.ParticipantesByUsuarioResponse;
import com.example.usuario.core.repository.UsuarioRepository;
import com.example.usuario.core.service.UsuarioService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository repository;
    private ParticipanteClient participanteClient;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository,ParticipanteClient participanteClient) {
        this.repository = repository;
        this.participanteClient = participanteClient;
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        if(!repository.existsByNombre(usuario.getNombre())){
            repository.save(usuario);
        }

    }

    @Override
    public void modificarUsuario(UsuarioDto usuarioDto, Long id) throws SearchException {
        if(repository.existsById(id)){
            usuarioDto.setId(id);
            repository.save(new Usuario(usuarioDto));
        }else{
            throw new SearchException("No existe el usuario a modificar");
        }
    }

    @Override
    public void eliminarUsuario(Long id) throws SearchException {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new SearchException("No existe el usuario a eliminar");
        }
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return repository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerUsuarioXId(Long id) throws SearchException {
        return Optional.ofNullable(repository.findById(id).orElseThrow(()-> new SearchException("No existe un usuario con ese ID")));
    }

    @Override
    public Usuario obtenerUsuarioXNombre(String nombre) throws SearchException {
        return repository.findByNombre(nombre);
    }

    @Override
    public boolean existeUsuario(Long id) throws SearchException {
        return repository.existsById(id);
    }

    @Override
    public Long existeUsuariobynombre(String nombre) throws SearchException {
        return repository.findIdByNombre(nombre);
    }

    @Override
    public ParticipantesByUsuarioResponse obtenerParticipanteXUsuario(Long idUsuario) {
        Usuario usuario = repository.findById(idUsuario).orElse(new Usuario());
        List<ParticipantesDto> participanteDtoList = participanteClient.obtenerParticipanteXUsuario(idUsuario);

        return ParticipantesByUsuarioResponse.builder()
                .nombre(usuario.getNombre())
                .noprofesional(usuario.getNoprofesional())
                    .participanteList(participanteDtoList)
            .build();
    }
}
