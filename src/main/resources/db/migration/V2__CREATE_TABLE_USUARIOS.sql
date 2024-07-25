create table fluxo.usuarios (
    id bigserial,
    nome_usuario text not null,
    senha text not null,
    ativo boolean not null,

    constraint pk_usuarios primary key (id)
);
