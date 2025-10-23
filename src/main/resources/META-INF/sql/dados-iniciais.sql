insert into cliente (id, nome, cpf, email, dataCadastro, version)
values (1, 'Ana Barros', '123.456.789-00', 'ana@demo.com', now(), 0);

insert into conta (id, agencia, numero, tipo, cliente_id, saldo, version)
values (1, '0001', '12345-6', 'CORRENTE', 1, 1000.00, 0);

