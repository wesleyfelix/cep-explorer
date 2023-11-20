create table ceps(

    id bigint not null auto_increment,
    cep varchar(9) not null,
    logradouro varchar(255),
    complemento varchar(255),
    bairro varchar(255),
    localidade varchar(255),
    uf varchar(2),
    ibge varchar(255),
    gia varchar(255),
    ddd varchar(5),
    siafi varchar(255),
    primary key(id)
);