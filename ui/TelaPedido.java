package com.empresa.ui;
import com.empresa.model.Pedido;
import com.empresa.model.Cliente;
import com.empresa.model.Produto;
import com.empresa.repository.ClienteRepository;
import com.empresa.repository.ProdutoRepository;
import com.empresa.repository.PedidoRepository;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TelaPedido extends JFrame {
  private final PedidoRepository pedidoRepo;
  private final ClienteRepository clienteRepo;
  private final ProdutoRepository produtoRepo;

  public TelaPedido(PedidoRepository pedidoRepo, ClienteRepository clienteRepo, ProdutoRepository produtoRepo) {
    super("Criação de Pedido");
    this.pedidoRepo = pedidoRepo;
    this.clienteRepo = clienteRepo;
    this.produtoRepo = produtoRepo;

    setSize(400, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    var panel = new JPanel(new BorderLayout());

    var clientes = new JComboBox<>(clienteRepo.findAll().toArray());
    var produtos = new JList<>(produtoRepo.findAll().toArray(new Produto[0]));
    produtos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    var btnSalvar = new JButton("Salvar Pedido");
    btnSalvar.addActionListener(e -> {
      Pedido pedido = new Pedido();
      pedido.setCliente((Cliente) clientes.getSelectedItem());
      pedido.setDataPedido(new Date());
      pedido.setProdutos(produtos.getSelectedValuesList());
      pedidoRepo.save(pedido);
      JOptionPane.showMessageDialog(this, "Pedido salvo!");
    });

    var form = new JPanel(new GridLayout(3, 1));
    form.add(new JLabel("Cliente:")); form.add(clientes);
    form.add(new JLabel("Produtos:")); form.add(new JScrollPane(produtos));
    panel.add(form, BorderLayout.CENTER);
    panel.add(btnSalvar, BorderLayout.SOUTH);

    add(panel);
    setVisible(true);
  }
}