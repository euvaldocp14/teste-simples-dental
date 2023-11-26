create table usuario
(

    id        UUID         not null,
    login     varchar(100) not null,
    senha     varchar(100) not null,
    pessoa_id UUID         not null,

    primary key (id),
    constraint fk_pessoa_id foreign key (pessoa_id) references pessoa (id)

);