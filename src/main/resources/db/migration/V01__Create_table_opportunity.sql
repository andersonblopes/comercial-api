create table opportunity (
  id bigint auto_increment not null,
  prospect_name varchar(80) not null,
  opportunity_description varchar(200) not null,
  price decimal(10,2),

  primary key (id)
);

insert into opportunity (prospect_name, opportunity_description, price) values ('Altran', 'Desenvolvimento de ESB', 950000);
insert into opportunity (prospect_name, opportunity_description, price) values ('Altran', 'Desenvolvimento de ERP com Angular e Spring', 056510);
insert into opportunity (prospect_name, opportunity_description, price) values ('Altran', 'Suporte Técnico', 200000);