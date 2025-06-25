create table  medicos (
                          id bigint not null auto_increment,
                          nombre varchar(255) not null,
                          email varchar(255) not null unique,
                          documento varchar(255) not null unique,
                          especialidad varchar(255) not null,
                          calle varchar(255) not null,
                          barrio varchar(255) not null,
                          codigo_postal varchar(255) not null,
                          complemento varchar(255),
                          numero varchar(255) not null,
                          estado char (100) not null,
                          ciudad varchar(255) not null,

                          primary key (id)
)