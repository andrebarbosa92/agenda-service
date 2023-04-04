package com.foxer.agenda.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxer.agenda.domain.entity.Paciente;
import com.foxer.agenda.domain.repository.PacienteRepository;
import com.foxer.agenda.exception.BusinessException;

//Caso der erro realiza rollback
@Transactional
@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public Paciente salvar(Paciente paciente) {
		
		boolean existeCpf = false;
		// TODO validar se o CPF ja existe
		Optional<Paciente> opt = pacienteRepository.findByCpf(paciente.getCpf());
		if(opt.isPresent()) {
			if(!opt.get().getId().equals(paciente.getId())) {
				existeCpf= true;
			}
		}
		
		if(existeCpf) {
			throw new BusinessException("CPF "+paciente.getCpf()+ " ja cadastrado!");
		}
			
		return pacienteRepository.save(paciente);
	}
	
	public List<Paciente> listarTodos(){
		return pacienteRepository.findAll();
	}
	
	public Optional<Paciente> buscarId(Long id) {
		return (pacienteRepository.findById(id));
	}
	
	public void deletar(Long id) {
		pacienteRepository.deleteById(id);
	}
	
	public Paciente alterar(Long id, Paciente paciente) {
		Optional<Paciente> opt = this.buscarId(id);
		
		if(opt.isEmpty()) {
			throw new BusinessException("Paciente não encontrado!");
		}
		
		paciente.setId(id);
		
		return this.salvar(paciente);
	}

}
