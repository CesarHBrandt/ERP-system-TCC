package com.empresa.controller;
import com.empresa.model.Pedido;
import com.empresa.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoRepository repo;
    public PedidoController(PedidoRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Pedido> listar() { return repo.findAll(); }

    @PostMapping
    public Pedido criar(@RequestBody Pedido p) { return repo.save(p); }
}
