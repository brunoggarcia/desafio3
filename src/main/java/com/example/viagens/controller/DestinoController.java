package com.example.viagens.controller;

import com.example.viagens.model.Destino;
import com.example.viagens.service.DestinoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

    private final DestinoService service;

    public DestinoController(DestinoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Destino> criar(@RequestBody Destino destino) {
        return ResponseEntity.status(201).body(service.criar(destino));
    }

    @GetMapping
    public List<Destino> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> detalhes(@PathVariable long id) {
        Destino d = service.buscarPorId(id);
        return d == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(d);
    }

    @GetMapping("/pesquisar")
    public List<Destino> pesquisar(@RequestParam String termo) {
        return service.pesquisarPorNomeOuLocalizacao(termo);
    }

    @PostMapping("/{id}/avaliar")
    public ResponseEntity<String> avaliar(
            @PathVariable long id,
            @RequestParam int nota) {

        if (nota < 1 || nota > 10) {
            return ResponseEntity.badRequest()
                    .body("A nota deve ser entre 1 e 10.");
        }

        Destino d = service.avaliar(id, nota);

        if (d == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok("Nova média: " + d.getMediaAvaliacoes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable long id) {
        if (service.excluir(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}