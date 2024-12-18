package com.example.usuario.core.serviceImpl;

import com.example.usuario.core.entities.Usuario;
import com.example.usuario.core.exception.SearchException;
import com.example.usuario.core.repository.UsuarioRepository;
import com.example.usuario.core.service.UsuarioService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository repository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        repository.save(usuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario) throws SearchException {
        if(repository.existsById(usuario.getId())){
            repository.save(usuario);
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
}
