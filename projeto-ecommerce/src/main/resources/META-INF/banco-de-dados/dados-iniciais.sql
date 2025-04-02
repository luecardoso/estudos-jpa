insert into Produto (id, nome, preco, descricao, data_criacao) values (3, 'Kindle', 499.0, 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.', CURRENT_TIMESTAMP);
insert into Produto (id, nome, preco, descricao, data_criacao) values (4, 'Câmera GoPro Hero 7', 1400.0, 'Desempenho 2x melhor.', CURRENT_TIMESTAMP);

insert into Cliente (id, nome, cpf) values (2, 'Felipe','123.456.789-00');
insert into Cliente (id, nome, cpf) values (3, 'Dani', '987.654.321-00');

insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (2, 0, CURRENT_TIMESTAMP));
insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (3, 0, CURRENT_TIMESTAMP));

insert into pedido (id, cliente_id, data_criacao, total, status) values (2, 2, CURRENT_TIMESTAMP, 998.0, 0);
insert into pedido (id, cliente_id, data_criacao, total, status) values (3, 2, CURRENT_TIMESTAMP, 499.0, 0);

insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (2, 3, 499, 2);
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (3, 3, 499, 1);

insert into categoria (id, nome) values (3, 'Eletrônicos');

insert into pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras) values (2, 1, 'cartao', '123', null);