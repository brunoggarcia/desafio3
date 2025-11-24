package com.example.viagens.service;

import com.example.viagens.model.Destino;
import com.example.viagens.repository.DestinoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DestinoService {

    private final DestinoRepository repository;

    public DestinoService(DestinoRepository repository) {
        this.repository = repository;
    }

    public Destino criar(Destino destino) {
        return repository.save(destino);
    }

    public List<Destino> listarTodos() {
        return repository.findAll();
    }

    public Destino buscarPorId(long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Destino> pesquisarPorNomeOuLocalizacao(String termo) {
        if (termo == null || termo.isBlank()) {
            return Collections.emptyList();
        }

        return repository.findByNomeContainingIgnoreCaseOrLocalizacaoContainingIgnoreCase(termo, termo);
    }

    public boolean excluir(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Destino avaliar(long id, int nota) {
        Destino destino = buscarPorId(id);

        if (destino != null) {
            destino.adicionarAvaliacao(nota);
            return repository.save(destino);
        }

        return null;
    }
}
