package com.foxer.agenda.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="agenda")
@Entity
public class Agenda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_agenda")
	private Long id;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="data_hora")
	private LocalDateTime horario;
	
	@Column(name="data_criacao")
	private LocalDateTime dataCriacao;
	
	@ManyToOne
	private Paciente paciente;
}
