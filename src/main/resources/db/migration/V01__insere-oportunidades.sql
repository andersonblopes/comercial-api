create table oportunidade (
  id bigint auto_increment not null,
  nome_prospecto varchar(80) not null,
  descricao varchar(200) not null,
  valor decimal(10,2),

  primary key (id)
);

insert into oportunidade (nome_prospecto, descricao, valor) values ('Altran Portugal', 'Desenvolvimento de ERP com Angular e Spring', 50000);