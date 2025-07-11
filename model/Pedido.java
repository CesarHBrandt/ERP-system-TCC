package com.empresa.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dataPedido;

    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name="Pedido_Produto",
      joinColumns=@JoinColumn(name="id_pedido"),
      inverseJoinColumns=@JoinColumn(name="id_produto"))
    private List<Produto> produtos;
}
