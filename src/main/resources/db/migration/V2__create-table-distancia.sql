create table distancia(

    id bigint not null auto_increment,
    cep_origem varchar(9) not null,
    cep_destino varchar(9) not null,
    url varchar(255),
    distancia_pe varchar(50),
    distancia_carro varchar(50),
    primary key(id)
);