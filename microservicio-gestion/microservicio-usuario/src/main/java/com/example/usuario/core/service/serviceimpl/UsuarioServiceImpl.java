package com.example.usuario.core.service.serviceimpl;

import com.example.usuario.core.dto.ParticipantesDto;
import com.example.usuario.core.dto.UsuarioDto;
import com.example.usuario.core.model.Rol;
import com.example.usuario.core.model.Usuario;
import com.example.usuario.core.exception.SearchException;
import com.example.usuario.core.feignClient.ParticipanteClient;
import com.example.usuario.core.http.response.ParticipantesByUsuarioResponse;
import com.example.usuario.core.repository.UsuarioRepository;
import com.example.usuario.core.service.UsuarioService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;
    private ParticipanteClient participanteClient;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ParticipanteClient participanteClient, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.participanteClient = participanteClient;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        if(!usuarioRepository.existsByNombre(usuario.getNombre())){
            usuarioRepository.save(usuario);
        }

    }

    @Override
    public void modificarUsuario(UsuarioDto usuarioDto, Long id, Rol rol) throws SearchException {
        if(usuarioRepository.existsById(id)){
            usuarioDto.setId(id);
            usuarioRepository.save(new Usuario(usuarioDto,rol,usuarioDto.getContrasenna()));
        }else{
            throw new SearchException("No existe el usuario a modificar");
        }
    }

    @Override
    public void eliminarUsuario(Long id) throws SearchException {
        if(usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
        }else{
            throw new SearchException("No existe el usuario a eliminar");
        }
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerUsuarioXId(Long id) throws SearchException {
        return Optional.ofNullable(usuarioRepository.findById(id).orElseThrow(()-> new SearchException("No existe un usuario con ese ID")));
    }

    @Override
    public Usuario obtenerUsuarioXNombre(String nombre) throws SearchException {
        return usuarioRepository.findByNombre(nombre).orElseThrow(()-> new SearchException("No existe un usuario con ese nombre"));
    }

    @Override
    public boolean existeUsuario(Long id) throws SearchException {
        return usuarioRepository.existsById(id);
    }

    @Override
    public Long existeUsuariobynombre(String nombre) throws SearchException {
        return usuarioRepository.findIdByNombre(nombre);
    }

    @Override
    public ParticipantesByUsuarioResponse obtenerParticipanteXUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(new Usuario());
        List<ParticipantesDto> participanteDtoList = participanteClient.obtenerParticipanteXUsuario(idUsuario);

        return ParticipantesByUsuarioResponse.builder()
                .nombre(usuario.getNombre())
                .noprofesional(usuario.getNoprofesional())
                    .participanteList(participanteDtoList)
            .build();
    }
}
