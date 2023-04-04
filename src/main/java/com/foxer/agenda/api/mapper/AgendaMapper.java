package com.foxer.agenda.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxer.agenda.api.request.AgendaRequest;
import com.foxer.agenda.api.response.AgendaResponse;
import com.foxer.agenda.domain.entity.Agenda;

@Component
public class AgendaMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Agenda toAgenda (AgendaRequest request) {
		return modelMapper.map(request, Agenda.class);
	}
	
	public AgendaResponse toAgendaResponse (Agenda agenda) {
		return modelMapper.map(agenda, AgendaResponse.class);
	}
	
	public List<AgendaResponse> toAgendaResponseList (List<Agenda> agendaList){
		return agendaList.stream().map(this::toAgendaResponse).collect(Collectors.toList());
	}
}
