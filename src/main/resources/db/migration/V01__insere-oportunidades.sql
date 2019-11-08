create table oportunidade (
  id bigint auto_increment not null,
  nome_prospecto varchar(80) not null,
  descricao varchar(200) not null,
  valor decimal(10,2),

  primary key (id)
);

insert into oportunidade (nome_prospecto, descricao, valor) values ('Digitalis', 'Desenvolvimento de ESB', 950000);
insert into oportunidade (nome_prospecto, descricao, valor) values ('Digitalis', 'Desenvolvimento de ERP com Angular e Spring', 056510);
insert into oportunidade (nome_prospecto, descricao, valor) values ('Digitalis', 'Suporte Técnico', 200000);
insert into oportunidade (nome_prospecto, descricao, valor) values ('Digitalis', 'Customização de Sistema', 5000);