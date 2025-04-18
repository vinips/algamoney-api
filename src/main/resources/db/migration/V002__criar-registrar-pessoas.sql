CREATE TABLE pessoa (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	logradouro VARCHAR(100),
	numero VARCHAR(5),
	bairro VARCHAR(50),
	cep VARCHAR(20),
	cidade VARCHAR(50),
	estado VARCHAR(20),
	ativo Boolean 	 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa(nome, logradouro, numero, bairro, cep, cidade, estado, ativo) VALUES ('Rafael Almeida', 'Rua das Andirobas', '195', 'Loteamento Garrafas', '88.268-314', 'Palhoça', 'SC', true);
INSERT INTO pessoa(nome, logradouro, numero, bairro, cep, cidade, estado, ativo) VALUES ('Fernanda', 'Rua das Carijobas', '2264', 'Vila Paraibas', '99.213-314', 'Graviá', 'MG', true);
INSERT INTO pessoa(nome, logradouro, numero, bairro, cep, cidade, estado, ativo) VALUES ('Rafaela', 'Rua das Andirobas', '195', 'Loteamento Garrafas', '44.136-265', 'Monte Carlo', 'PE', false);
INSERT INTO pessoa(nome, logradouro, numero, bairro, cep, cidade, estado, ativo) VALUES ('Rapoter', 'Rua das Andirobas', '195', 'Loteamento Garrafas', '44.874-877', 'Itaimmirim', 'AL', true);
INSERT INTO pessoa(nome, logradouro, numero, bairro, cep, cidade, estado, ativo) VALUES ('Rafael', 'Rua das Jarapenhas', NULL, 'Loteamento Garrafas', NULL, 'Palhoça', NULL, 0);
INSERT INTO pessoa(nome, logradouro, numero, bairro, cep, cidade, estado, ativo) VALUES ('Rafael Gemeo', NULL, '1333', 'Vila Petrópolis', NULL, 'Atibaia', NULL, 0);
INSERT INTO pessoa(nome, logradouro, numero, bairro, cep, cidade, estado, ativo) VALUES ('Jorginho Violao', 'Rua das Garrafinhas', '12295', 'Loteamento Caixas', '44.874-877', 'Itaimmirim', 'AL', 1);
INSERT INTO pessoa(nome, logradouro, numero, bairro, cep, cidade, estado, ativo) VALUES ('Jorginho Violaozao', 'Rua das Garrafinhas', '12295', 'Loteamento Caixas', '44.874-877', 'Itaimmirim', 'AL', 1);
INSERT INTO pessoa(nome, logradouro, numero, bairro, cep, cidade, estado, ativo) VALUES ('Rafinha Violaozao', 'Rua das Garrafinhas', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO pessoa(nome, logradouro, numero, bairro, cep, cidade, estado, ativo) VALUES ('Testando', 'Rua das Garrafinhas', NULL, NULL, NULL, NULL, NULL, 1);