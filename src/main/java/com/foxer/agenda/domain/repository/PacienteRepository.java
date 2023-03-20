package com.foxer.agenda.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foxer.agenda.domain.entity.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

	Optional<Paciente> findByCpf(String cpf);
}
