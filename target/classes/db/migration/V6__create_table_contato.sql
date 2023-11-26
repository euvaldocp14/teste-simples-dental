create table contato
(

    id               UUID          not null,
    nome             varchar(255)  not null,
    contato          varchar(255)  not null,
    data_criacao     date          not null,
    profissional_id  UUID          not null,

    primary key (id),
    constraint fk_profissional_id foreign key (profissional_id) references profissional (id)

);