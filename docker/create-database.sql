CREATE DATABASE kumulus;

\c kumulus;

CREATE SEQUENCE pessoa_seq;

CREATE TABLE pessoa (
    id INT NOT NULL DEFAULT nextval('pessoa_seq'),
    nome VARCHAR(20) NOT NULL,
    dataNascimento DATE NOT NULL,
    sexo VARCHAR(1) NOT NULL,
    CONSTRAINT PK_Pessoa PRIMARY KEY (id),
    CONSTRAINT UC_Pessoa UNIQUE (nome, dataNascimento, sexo)
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
  CONSTRAINT FK_Endereco_Pessoa FOREIGN KEY (idPessoa) REFERENCES pessoa (id),
  CONSTRAINT UC_Endereco UNIQUE (estado, cidade, logradouro, numero, bairro, cep, idPessoa)
);

INSERT INTO pessoa(nome, dataNascimento, sexo)
VALUES
('Ana Silva', '1983-07-15', 'F'),
('Carlos Souza', '1990-11-23', 'M'),
('Lucas Neves', '1975-02-05', 'M'),
('Mariana Lima', '1988-03-29', 'F'),
('Roberto Dias', '1992-05-21', 'M'),
('Fernanda Costa', '1985-08-11', 'F'),
('Tiago Amaral', '1979-12-12', 'M'),
('Patricia Freitas', '1994-01-07', 'F'),
('Eduardo Rocha', '1980-09-16', 'M'),
('Camila Gomes', '1991-04-03', 'F'),
('Rafael Martins', '1986-06-24', 'M'),
('Juliana Garcia', '1978-07-30', 'F'),
('Diego Alves', '1995-10-09', 'M'),
('Isabela Moura', '1982-11-25', 'F'),
('Bruno Henrique', '1993-03-14', 'M');

INSERT INTO endereco(estado, cidade, logradouro, numero, bairro, cep, idPessoa) VALUES
('SP', 'São Paulo', 'Rua das Flores', 150, 'Jardim Paulista', '12345000', 1),
('RJ', 'Rio de Janeiro', 'Avenida Atlântica', 321, 'Copacabana', '12346000', 2),
('MG', 'Belo Horizonte', 'Avenida Afonso Pena', 400, 'Centro', '12347000', 3),
('RS', 'Porto Alegre', 'Rua dos Andradas', 1234, 'Centro Histórico', '12348000', 4),
('BA', 'Salvador', 'Avenida Oceanica', 567, 'Barra', '12349000', 5),
('PE', 'Recife', 'Rua da Aurora', 890, 'Boa Vista', '12341000', 6),
('CE', 'Fortaleza', 'Avenida Beira Mar', 1020, 'Meireles', '12342000', 7),
('PA', 'Belém', 'Travessa Humaitá', 345, 'Marco', '12343000', 8),
('AM', 'Manaus', 'Avenida Brasil', 678, 'Compensa', '12344000', 9),
('PR', 'Curitiba', 'Rua XV de Novembro', 789, 'Centro', '12345001', 10),
('SC', 'Florianópolis', 'Avenida Beira Mar Norte', 890, 'Centro', '12345002', 11),
('RS', 'Porto Alegre', 'Avenida Ipiranga', 1001, 'Praia de Belas', '12345003', 12),
('MS', 'Campo Grande', 'Avenida Afonso Pena', 1112, 'Centro', '12345004', 13),
('MT', 'Cuiabá', 'Avenida do CPA', 1213, 'Centro Político Administrativo', '12345005', 14),
('GO', 'Goiânia', 'Avenida Goiás', 1314, 'Setor Central', '12345006', 15);

INSERT INTO endereco(estado, cidade, logradouro, numero, bairro, cep, idPessoa) VALUES
('SP', 'Campinas', 'Avenida Brasil', 234, 'Centro', '23456000', 1),
('RJ', 'Niterói', 'Rua da Conceição', 456, 'Centro', '23457000', 2),
('MG', 'Juiz de Fora', 'Avenida Rio Branco', 567, 'Centro', '23458000', 3),
('RS', 'Pelotas', 'Rua XV de Novembro', 678, 'Centro', '23459000', 4),
('BA', 'Feira de Santana', 'Avenida Getúlio Vargas', 789, 'Centro', '23451000', 5),
('PE', 'Olinda', 'Avenida Ministro Marcos Freire', 890, 'Bairro Novo', '23452000', 6),
('CE', 'Caucaia', 'Rua Coronel Correia', 101, 'Centro', '23453000', 7),
('PA', 'Santarém', 'Avenida Mendonça Furtado', 212, 'Aldeia', '23454000', 8),
('AM', 'Parintins', 'Rua Vieira Junior', 323, 'Centro', '23455000', 9),
('PR', 'Londrina', 'Avenida Higienópolis', 434, 'Centro', '23456001', 10),
('SC', 'Joinville', 'Rua das Palmeiras', 545, 'Centro', '23456002', 11),
('RS', 'Caxias do Sul', 'Avenida Júlio de Castilhos', 656, 'Centro', '23456003', 12),
('MS', 'Dourados', 'Avenida Marcelino Pires', 767, 'Centro', '23456004', 13),
('MT', 'Rondonópolis', 'Avenida Lions Internacional', 878, 'Centro', '23456005', 14),
('GO', 'Anápolis', 'Avenida Brasil', 989, 'Jundiaí', '23456006', 15);

