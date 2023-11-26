create table pessoa
(

    id                 UUID         not null,
    nome               varchar(100) not null,
    data_nascimento   date         not null,
    genero             varchar(100),

    primary key (id)

);