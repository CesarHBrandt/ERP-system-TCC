package com.empresa.ui;
import com.empresa.model.Cliente;
import com.empresa.repository.ClienteRepository;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCliente extends JFrame {
    private final ClienteRepository repo;
    private final DefaultListModel<Cliente> listModel = new DefaultListModel<>();

    public TelaCliente(ClienteRepository repo) {
        super("Cadastro de Clientes");
        this.repo = repo;
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        var panel = new JPanel(new BorderLayout());
        var list = new JList<>(listModel);
        refreshList();
        panel.add(new JScrollPane(list), BorderLayout.CENTER);

        var form = new JPanel(new GridLayout(4, 2));
        var nomeF = new JTextField(); var emailF = new JTextField(); var telF = new JTextField();
        form.add(new JLabel("Nome:")); form.add(nomeF);
        form.add(new JLabel("Email:")); form.add(emailF);
        form.add(new JLabel("Telefone:")); form.add(telF);
        var btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            Cliente c = new Cliente();
            c.setNome(nomeF.getText());
            c.setEmail(emailF.getText());
            c.setTelefone(telF.getText());
            repo.save(c);
            nomeF.setText(""); emailF.setText(""); telF.setText("");
            refreshList();
        });
        form.add(btnSalvar);
        panel.add(form, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private void refreshList() {
        listModel.clear();
        List<Cliente> todos = repo.findAll();
        todos.forEach(listModel::addElement);
    }
}
