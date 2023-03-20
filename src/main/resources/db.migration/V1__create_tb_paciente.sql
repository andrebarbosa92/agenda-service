CREATE TABLE paciente (
    id_paciente integer NOT NULL,
    nome varchar(50) NOT NULL,
    sobrenome varchar(100) NOT NULL,
    cpf varchar(15) NOT NULL,
    email varchar(100),
    constraint id_paciente_PK primary key(id_paciente)
);