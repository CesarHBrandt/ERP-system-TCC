package com.empresa.controller;
import com.empresa.model.Produto;
import com.empresa.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoRepository repo;
    public ProdutoController(ProdutoRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Produto> listar() { return repo.findAll(); }

    @PostMapping
    public Produto criar(@RequestBody Produto p) { return repo.save(p); }
}
