package com.eduardo.avlApiRest.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardo.avlApiRest.dto.MotoCreateDTO;
import com.eduardo.avlApiRest.dto.MotoResponseDTO;
import com.eduardo.avlApiRest.modelo.Moto;

@Component
public class MotoMapper {
	@Autowired
	private ModelMapper mapper;
	public Moto toEntity(MotoCreateDTO dto) {
	Moto moto = mapper.map(dto, Moto.class);
		moto.setMarca(dto.getMarca());
		moto.setModelo(dto.getModelo());
		moto.setCor(dto.getCor());
		moto.setKm(dto.getKm());
		moto.setCons(dto.getCons());
		return moto;
	}	
	public MotoResponseDTO toDTO(Moto entity) {
	MotoResponseDTO dto = mapper.map(entity, MotoResponseDTO.class);
		return dto;
	}
	
	public List<MotoResponseDTO> toDTO(List<Moto> motos) {
return motos.stream().map(moto-> toDTO(moto))
.collect(Collectors.toList());
}
}
