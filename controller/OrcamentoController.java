package com.empresa.controller;
import com.empresa.model.Orcamento;
import com.empresa.repository.OrcamentoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orcamentos")
public class OrcamentoController {
    private final OrcamentoRepository repo;
    public OrcamentoController(OrcamentoRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Orcamento> listar() { return repo.findAll(); }

    @PostMapping
    public Orcamento criar(@RequestBody Orcamento o) { return repo.save(o); }
}
