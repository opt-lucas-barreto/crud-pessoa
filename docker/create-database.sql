CREATE DATABASE kumulus;

\c kumulus;

CREATE SEQUENCE pessoa_seq;

CREATE TABLE pessoa (
    id INT NOT NULL DEFAULT nextval('pessoa_seq'),
    nome VARCHAR(20) NOT NULL,
    dataNascimento DATE NOT NULL,
    sexo VARCHAR(1) NOT NULL,
    CONSTRAINT PK_Pessoa PRIMARY KEY (id)
);

CREATE SEQUENCE endereco_seq;

CREATE TABLE endereco (
  id INT NOT NULL DEFAULT nextval('endereco_seq'),
  estado VARCHAR(2) NOT NULL,
  cidade VARCHAR(100) NOT NULL,
  logradouro VARCHAR(100) NOT NULL,
  numero INT NOT NULL,
  bairro VARCHAR(100) NOT NULL,
  cep VARCHAR(8) NOT NULL,
  idPessoa INT NOT NULL,
  CONSTRAINT PK_Endereco PRIMARY KEY (id),
  CONSTRAINT FK_Endereco_Pessoa FOREIGN KEY (idPessoa) REFERENCES pessoa (id)
);

INSERT INTO pessoa (id, nome, dataNascimento, sexo)
VALUES (1, 'João Silva', '1985-04-12', 'M'),
       (2, 'Maria Oliveira', '1990-08-25', 'F'),
       (3, 'Carlos Pereira', '1975-11-03', 'M');

INSERT INTO endereco (id, estado, cidade, logradouro, numero, bairro, cep, idPessoa)
VALUES (1, 'SP', 'São Paulo', 'Rua das Flores', 123, 'Jardim das Rosas', '12345678', 1),
       (2, 'RJ', 'Rio de Janeiro', 'Avenida Atlântica', 321, 'Copacabana', '23456789', 2),
       (3, 'MG', 'Belo Horizonte', 'Praça da Liberdade', 456, 'Savassi', '34567890', 3);