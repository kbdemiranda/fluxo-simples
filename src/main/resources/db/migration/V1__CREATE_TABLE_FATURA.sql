create schema if not exists fluxo;

create table fluxo.fatura (
    id bigserial,
    descricao text not null,
    valor numeric(10, 2) not null,
    data_vencimento date not null,
    data_pagamento date,
    paga boolean not null,

    constraint pk_fatura primary key (id)
);