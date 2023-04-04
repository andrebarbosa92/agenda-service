package com.foxer.agenda.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxer.agenda.api.mapper.PacienteMapper;
import com.foxer.agenda.api.request.PacienteRequest;
import com.foxer.agenda.api.response.PacienteResponse;
import com.foxer.agenda.domain.entity.Paciente;
import com.foxer.agenda.domain.service.PacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/paciente")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private PacienteMapper pacienteMapper;
	
	@PostMapping
	public ResponseEntity<PacienteResponse> salvar(@Valid @RequestBody PacienteRequest  pacienteRequest){
		
		Paciente paciente = pacienteMapper.toPaciente(pacienteRequest);
		Paciente pacienteSalvo = pacienteService.salvar(paciente);
		PacienteResponse pacienteResponse = pacienteMapper.toPacienteResponse(pacienteSalvo);
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);
	}
	
	@GetMapping
	public ResponseEntity<List<PacienteResponse>> listarTodos(){
		
		List<Paciente> pacientes = pacienteService.listarTodos();
		List<PacienteResponse> responseList = pacienteMapper.toPacienteResponseList(pacientes);
		return ResponseEntity.status(HttpStatus.OK).body(responseList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PacienteResponse> buscarId(@PathVariable Long id){
		
		Optional<Paciente> optPaciente = pacienteService.buscarId(id);
		Paciente paciente = optPaciente.get();
		PacienteResponse optResponse = pacienteMapper.toPacienteResponse(paciente);
		
		if(optPaciente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(optResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarId (@PathVariable Long id){
		
		pacienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PacienteResponse> alterar (@PathVariable Long id, @RequestBody PacienteRequest  pacienteRequest){
		
		Paciente paciente = pacienteMapper.toPaciente(pacienteRequest);
		Paciente pacienteSalvo = pacienteService.alterar(id, paciente);
		PacienteResponse pacienteResponse = pacienteMapper.toPacienteResponse(pacienteSalvo);
		return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
	}
	
}
