package com.example.viagens.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    private Destino destino;

    private LocalDate dataReserva = LocalDate.now();

    public Reserva() {}

    public Reserva(Usuario usuario, Destino destino) {
        this.usuario = usuario;
        this.destino = destino;
    }

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public Destino getDestino() { return destino; }
    public LocalDate getDataReserva() { return dataReserva; }
}
