create table city
(
    id    bigint auto_increment not null,
    name  varchar(80)           not null,
    state varchar(200)          not null,
    primary key (id)
);

insert into city (name, state)
values ('Lisboa', 'Lisboa');
insert into city (name, state)
values ('Porto', 'Porto');
insert into city (name, state)
values ('Coimbra', 'Leiria');
