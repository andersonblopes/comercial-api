-- Creates city table
create table city
(
    id    bigint auto_increment not null,
    name  varchar(80)           not null,
    state varchar(200)          not null,
    primary key (id)
);

-- Creates opportunity table
create table opportunity
(
    id                      bigint auto_increment not null,
    prospect_name           varchar(80)           not null,
    opportunity_description varchar(200)          not null,
    fk_city                 integer               not null,
    price                   decimal(10, 2),

    primary key (id)
);

-- Includes foreign key
ALTER TABLE opportunity
    ADD CONSTRAINT fk_city FOREIGN KEY (fk_city) REFERENCES city (id);

-- Inserts cities
insert into city (name, state)
values ('Lisboa', 'Lisboa');
insert into city (name, state)
values ('Porto', 'Porto');
insert into city (name, state)
values ('Coimbra', 'Leiria');

-- Inserts opportunities
insert into opportunity (prospect_name, opportunity_description, price, fk_city)
values ('Altran', 'Desenvolvimento de ESB', 950000, 1);
insert into opportunity (prospect_name, opportunity_description, price, fk_city)
values ('Altran', 'Desenvolvimento de ERP com Angular e Spring', 056510, 2);
insert into opportunity (prospect_name, opportunity_description, price, fk_city)
values ('Altran', 'Suporte Técnico', 200000, 3);
