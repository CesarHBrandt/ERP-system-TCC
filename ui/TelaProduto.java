package com.empresa.ui;
import com.empresa.model.Produto;
import com.empresa.repository.ProdutoRepository;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaProduto extends JFrame {
  private final ProdutoRepository repo;
  private final DefaultListModel<Produto> listModel = new DefaultListModel<>();

  public TelaProduto(ProdutoRepository repo) {
    super("Cadastro de Produtos");
    this.repo = repo;
    setSize(400,300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    var panel = new JPanel(new BorderLayout());
    var list = new JList<>(listModel);
    refreshList();
    panel.add(new JScrollPane(list), BorderLayout.CENTER);

    var form = new JPanel(new GridLayout(5,2));
    var nomeF = new JTextField(); var precoF = new JTextField(); var estoqueF = new JTextField();
    form.add(new JLabel("Nome:")); form.add(nomeF);
    form.add(new JLabel("Preço:")); form.add(precoF);
    form.add(new JLabel("Estoque:")); form.add(estoqueF);
    var btnSalvar = new JButton("Salvar");
    btnSalvar.addActionListener(e -> {
      Produto p = new Produto();
      p.setNome(nomeF.getText());
      p.setPreco(Double.valueOf(precoF.getText()));
      p.setEstoque(Integer.valueOf(estoqueF.getText()));
      repo.save(p);
      nomeF.setText(""); precoF.setText(""); estoqueF.setText("");
      refreshList();
    });
    form.add(btnSalvar);
    panel.add(form, BorderLayout.SOUTH);

    add(panel);
    setVisible(true);
  }

  private void refreshList() {
    listModel.clear();
    List<Produto> todos = repo.findAll();
    todos.forEach(listModel::addElement);
  }
}