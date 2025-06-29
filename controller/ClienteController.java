package com.empresa.controller;
import com.empresa.model.Cliente;
import com.empresa.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteRepository repo;
    public ClienteController(ClienteRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Cliente> listar() { return repo.findAll(); }

    @PostMapping
    public Cliente criar(@RequestBody Cliente c) { return repo.save(c); }
}
