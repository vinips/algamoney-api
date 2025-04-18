CREATE TABLE lancamento (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_categoria BIGINT(20) NOT NULL,
	id_pessoa BIGINT(20) NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	FOREIGN KEY (id_categoria) REFERENCES categoria(id),
	FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (1, 1, 'Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA');
INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (2, 2, 'Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'DESPESA');
INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (2, 2, 'Top Club', '2017-06-10', null, 120, null, 'RECEITA');
INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (3, 4, 'CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'RECEITA');
INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (3, 5, 'DMAE', '2017-06-10', null, 200.30, null, 'DESPESA');
INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (4, 6, 'Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'RECEITA');
INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (1, 6, 'Bahamas', '2017-06-10', null, 500, null, 'RECEITA');
INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (4, 8, 'Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'DESPESA');
INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (3, 9, 'Despachante', '2017-06-10', null, 123.64, 'Multas', 'DESPESA');
INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (5, 10, 'Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'RECEITA');
INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (1, 5, 'Café', '2017-06-10', null, 8.32, null, 'DESPESA');
INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (5, 4, 'Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'DESPESA');
INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (4, 3, 'Instrumentos', '2017-06-10', null, 1040.32, null, 'DESPESA');
INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (4, 2, 'Café', '2017-04-10', '2017-04-10', 4.32, null, 'DESPESA');
INSERT INTO lancamento (id_categoria, id_pessoa, descricao, data_vencimento, data_pagamento, valor, observacao, tipo) values (4, 1, 'Lanche', '2017-06-10', null, 10.20, null, 'DESPESA');