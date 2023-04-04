package com.foxer.agenda.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxer.agenda.api.mapper.AgendaMapper;
import com.foxer.agenda.api.request.AgendaRequest;
import com.foxer.agenda.api.response.AgendaResponse;
import com.foxer.agenda.domain.entity.Agenda;
import com.foxer.agenda.domain.service.AgendaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/agenda")
public class AgendaController {

	@Autowired
	private AgendaService agendaService;
	
	@Autowired
	private AgendaMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<AgendaResponse>> listarTodos (){
		List<Agenda> agendaList = agendaService.listarTodos();
		List<AgendaResponse> responseList = mapper.toAgendaResponseList(agendaList);
		return ResponseEntity.ok().body(responseList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AgendaResponse> buscarId (@PathVariable Long id){
		Optional<Agenda> optAgenda = agendaService.buscarId(id);
		
		if(optAgenda.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Agenda agenda = optAgenda.get();
		AgendaResponse response = mapper.toAgendaResponse(agenda);
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping
	public ResponseEntity<AgendaResponse> salvar(@Valid @RequestBody AgendaRequest request){
		Agenda agenda = mapper.toAgenda(request);
		Agenda agendaSalvo = agendaService.salvar(agenda);
		AgendaResponse response = mapper.toAgendaResponse(agendaSalvo);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
