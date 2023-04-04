package com.foxer.agenda.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxer.agenda.api.request.PacienteRequest;
import com.foxer.agenda.api.response.PacienteResponse;
import com.foxer.agenda.domain.entity.Paciente;

@Component
public class PacienteMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	
	public Paciente toPaciente(PacienteRequest pacienteRequest) {
		return modelMapper.map(pacienteRequest, Paciente.class);
	}
	
	public PacienteResponse toPacienteResponse(Paciente paciente) {
		return modelMapper.map(paciente, PacienteResponse.class);
	}

	public List<PacienteResponse> toPacienteResponseList (List<Paciente> paciente){
		return paciente.stream().map(this::toPacienteResponse).collect(Collectors.toList());
		
	}
}
