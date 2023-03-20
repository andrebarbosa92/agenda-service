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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxer.agenda.domain.entity.Paciente;
import com.foxer.agenda.domain.service.PacienteService;

@RestController
@RequestMapping("/api/v1/paciente")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	
	@PostMapping
	public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente){
		Paciente pacienteSalvo = pacienteService.salvar(paciente);
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<Paciente>> listarTodos(){
		return ResponseEntity.status(HttpStatus.OK).body(pacienteService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> buscarId(@PathVariable Long id){
		
		Optional<Paciente> opt = pacienteService.buscarId(id);
		
		if(opt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
	
		return ResponseEntity.status(HttpStatus.OK).body(opt.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarId (@PathVariable Long id){
		pacienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
}
