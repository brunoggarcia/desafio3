package com.example.viagens.service;

import com.example.viagens.model.Destino;
import com.example.viagens.model.Usuario;
import com.example.viagens.model.Reserva;
import com.example.viagens.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Transactional
    public Reserva criarReserva(Usuario usuario, Destino destino) {
        Reserva reserva = new Reserva(usuario, destino);
        return reservaRepository.save(reserva);
    }
}
