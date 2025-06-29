-- Criação do banco e tabelas
DROP DATABASE IF EXISTS empresa;
CREATE DATABASE empresa CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE empresa;

CREATE TABLE Cliente (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  telefone VARCHAR(20) NOT NULL
);

CREATE TABLE Produto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  preco DECIMAL(10,2) NOT NULL,
  estoque INT NOT NULL
);

CREATE TABLE Pedido (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_cliente INT NOT NULL,
  data_pedido DATE NOT NULL,
  FOREIGN KEY (id_cliente) REFERENCES Cliente(id) ON DELETE RESTRICT
);

CREATE TABLE Pedido_Produto (
  id_pedido INT NOT NULL,
  id_produto INT NOT NULL,
  quantidade INT NOT NULL,
  PRIMARY KEY (id_pedido, id_produto),
  FOREIGN KEY (id_pedido) REFERENCES Pedido(id) ON DELETE CASCADE,
  FOREIGN KEY (id_produto) REFERENCES Produto(id) ON DELETE RESTRICT
);

CREATE TABLE Orcamento (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_cliente INT NOT NULL,
  data_orcamento DATE NOT NULL,
  FOREIGN KEY (id_cliente) REFERENCES Cliente(id) ON DELETE RESTRICT
);

CREATE TABLE Orcamento_Produto (
  id_orcamento INT NOT NULL,
  id_produto INT NOT NULL,
  quantidade INT NOT NULL,
  PRIMARY KEY (id_orcamento, id_produto),
  FOREIGN KEY (id_orcamento) REFERENCES Orcamento(id) ON DELETE CASCADE,
  FOREIGN KEY (id_produto) REFERENCES Produto(id) ON DELETE RESTRICT
);

-- Povoamento inicial
INSERT INTO Cliente (nome, email, telefone) VALUES
  ('Ana Silva','ana@mail.com','11 99999‑0001'),
  ('Bruno Costa','bruno@mail.com','11 98888‑0002');

INSERT INTO Produto (nome, preco, estoque) VALUES
  ('Camiseta',49.90,150),
  ('Calça Jeans',119.90,80);
