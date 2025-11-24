package com.example.viagens.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String localizacao;
    private String descricao;
    private Double precoPacote;
    private boolean disponivel = true;

    private int totalAvaliacoes = 0;
    private double somaAvaliacoes = 0;
    private double mediaAvaliacoes = 0;

    public Destino() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    public double getMediaAvaliacoes() { return mediaAvaliacoes; }

    public void adicionarAvaliacao(int nota) {
        this.totalAvaliacoes++;
        this.somaAvaliacoes += nota;
        this.mediaAvaliacoes = somaAvaliacoes / totalAvaliacoes;
    }
}
