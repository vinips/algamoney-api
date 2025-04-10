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

INSERT INTO pessoa(nome, logradouro, numero, bairro, cep, cidade, estado, ativo) values ("Rafael", "Rua das Andirobas", "195", "Loteamento Garrafas", "88.268-314", "Palhoça", "SC", true);
INSERT INTO pessoa(nome, logradouro, numero, bairro, cep, cidade, estado, ativo) values ("Fernanda", "Rua das Carijobas", "2264", "Vila Paraibas", "99.213-314", "Graviá", "MG", true);
INSERT INTO pessoa(nome, logradouro, numero, bairro, cep, cidade, estado, ativo) values ("Rafael", "Rua das Andirobas", "195", "Loteamento Garrafas", "44.136-265", "Monte Carlo", "PE", false);
INSERT INTO pessoa(nome, logradouro, numero, bairro, cep, cidade, estado, ativo) values ("Rafael", "Rua das Andirobas", "195", "Loteamento Garrafas", "44.874-877", "Itaimmirim", "AL", true);