create table profissional
(

    id               UUID          not null,
    nome             varchar(255)  not null,
    cargo            varchar(255)  not null,
    data_nascimento  date          not null,
    data_criacao     date          not null,
    ativo            boolean       not null,

    primary key (id)

);