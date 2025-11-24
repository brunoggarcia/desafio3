package com.example.viagens.repository;

import com.example.viagens.model.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DestinoRepository extends JpaRepository<Destino, Long> {
    List<Destino> findByNomeContainingIgnoreCaseOrLocalizacaoContainingIgnoreCase(String nome, String localizacao);
}
