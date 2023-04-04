package com.foxer.agenda.domain.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foxer.agenda.domain.entity.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long>{

	Optional<Agenda> findByHorario(LocalDateTime horario);

}
