CREATE TABLE agenda (
    id_agenda integer NOT NULL,
    descricao varchar(255),
    data_hora timestamp,
    data_criacao timestamp,
    id_paciente integer,
    constraint id_PK primary key(id_agenda),
    constraint agenda_paciente_FK foreign key(id_paciente) references paciente(id_paciente)
);