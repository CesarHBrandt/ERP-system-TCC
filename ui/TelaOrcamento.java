package com.empresa.ui;
import com.empresa.model.Orcamento;
import com.empresa.model.Cliente;
import com.empresa.model.Produto;
import com.empresa.repository.ClienteRepository;
import com.empresa.repository.ProdutoRepository;
import com.empresa.repository.OrcamentoRepository;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TelaOrcamento extends JFrame {
  private final OrcamentoRepository orcamentoRepo;
  private final ClienteRepository clienteRepo;
  private final ProdutoRepository produtoRepo;

  public TelaOrcamento(OrcamentoRepository orcamentoRepo, ClienteRepository clienteRepo, ProdutoRepository produtoRepo) {
    super("Criação de Orçamento");
    this.orcamentoRepo = orcamentoRepo;
    this.clienteRepo = clienteRepo;
    this.produtoRepo = produtoRepo;

    setSize(400, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    var panel = new JPanel(new BorderLayout());

    var clientes = new JComboBox<>(clienteRepo.findAll().toArray());
    var produtos = new JList<>(produtoRepo.findAll().toArray(new Produto[0]));
    produtos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    var btnSalvar = new JButton("Salvar Orçamento");
    btnSalvar.addActionListener(e -> {
      Orcamento orc = new Orcamento();
      orc.setCliente((Cliente) clientes.getSelectedItem());
      orc.setDataOrcamento(new Date());
      orc.setProdutos(produtos.getSelectedValuesList());
      orcamentoRepo.save(orc);
      JOptionPane.showMessageDialog(this, "Orçamento salvo!");
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