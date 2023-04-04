package com.foxer.agenda.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxer.agenda.domain.entity.Agenda;
import com.foxer.agenda.domain.entity.Paciente;
import com.foxer.agenda.domain.repository.AgendaRepository;
import com.foxer.agenda.exception.BusinessException;

@Service
@Transactional
public class AgendaService {
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private PacienteService pacienteService;

	public List<Agenda> listarTodos(){
		
		return agendaRepository.findAll();
	}
	
	public Optional<Agenda> buscarId(Long id) {
		
		return agendaRepository.findById(id);
	}
	
	public Agenda salvar (Agenda agenda) {
		
		Optional<Paciente> optPaciente =  pacienteService.buscarId(agenda.getPaciente().getId());
		
		if(optPaciente.isEmpty()) {
			throw new BusinessException("Paciente não encontrado.");
		}
		
		Optional<Agenda> optAgenda = agendaRepository.findByHorario(agenda.getHorario());
		
		if(optAgenda.isPresent()) {
			throw new BusinessException("Horário indisponível");
		}
		
		agenda.setPaciente(optPaciente.get());
		agenda.setDataCriacao(LocalDateTime.now());
		
		return agendaRepository.save(agenda);
	}
}
