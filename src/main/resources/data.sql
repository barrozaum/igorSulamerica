insert into CARGO (id, descricao_cargo) values (null, 'Gerente');
insert into CARGO (id, descricao_cargo) values (null, 'Administrativo');
insert into CARGO (id, descricao_cargo) values (null, 'Supervisor');

insert into perfil (id, descricao_perfil) values (null, 'Administrador');
insert into perfil (id, descricao_perfil) values (null, 'Comum');

insert into usuario (ativo, cpf, data_nascimento, nome, sexo, cargo_id, perfil_id) values (true, '14037401002', '1993-01-06', 'Igor barrozo brasil', 'M' ,1,2);
insert into usuario (ativo, cpf, data_nascimento, nome, sexo, cargo_id, perfil_id) values (true, '09241511028', '1983-01-06', 'Bruno barrozo brasil', 'M' ,1,1);
insert into usuario (ativo, cpf, data_nascimento, nome, sexo, cargo_id, perfil_id) values (true, '03554004057', '2005-03-07', 'Marina barrozo brasil', 'F' ,1,2);
insert into usuario (ativo, cpf, data_nascimento, nome, sexo, cargo_id, perfil_id) values (false, '57198191002', '1960-02-03', 'Eliane barrozo brasil', 'F' ,1,2);