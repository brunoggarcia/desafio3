package com.example.viagens.controller;

import com.example.viagens.model.Destino;
import com.example.viagens.model.Usuario;
import com.example.viagens.service.DestinoService;
import com.example.viagens.service.ReservaService;
import com.example.viagens.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final DestinoService destinoService;
    private final UsuarioService usuarioService;

    public ReservaController(ReservaService reservaService,
                             DestinoService destinoService,
                             UsuarioService usuarioService) {
        this.reservaService = reservaService;
        this.destinoService = destinoService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/{destinoId}")
    public ResponseEntity<String> reservar(@PathVariable Long destinoId, Authentication auth) {
        Destino destino = destinoService.buscarPorId(destinoId);

        if (destino == null) {
            throw new RuntimeException("Destino não encontrado");
        }

        Usuario usuario = usuarioService.buscarPorEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        reservaService.criarReserva(usuario, destino);

        return ResponseEntity.ok("Reserva realizada com sucesso!");
    }
}
