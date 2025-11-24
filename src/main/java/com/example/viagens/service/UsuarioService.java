package com.example.viagens.service;

import com.example.viagens.model.Usuario;
import com.example.viagens.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrar(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
