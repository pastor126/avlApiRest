package com.eduardo.avlApiRest.controle;

import java.util.List;

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

import com.eduardo.avlApiRest.dto.MotoCreateDTO;
import com.eduardo.avlApiRest.dto.MotoResponseDTO;
import com.eduardo.avlApiRest.dto.mapper.MotoMapper;
import com.eduardo.avlApiRest.excecao.MotoNotFoundException;
import com.eduardo.avlApiRest.modelo.Moto;
import com.eduardo.avlApiRest.servico.MotoServico;

import jakarta.validation.Valid;

@RestController
@RequestMapping("motos")
public class MotoControle {
	@Autowired
	private MotoServico motoServico;
	@Autowired
	private MotoMapper motoMapper;

	@PostMapping
	public ResponseEntity<MotoResponseDTO> gravar(@RequestBody @Valid MotoCreateDTO motoCreateDTO) {
		Moto moto = motoMapper.toEntity(motoCreateDTO);
		Moto motoGravada = motoServico.salvar(moto);
		MotoResponseDTO motoResponseDTO = motoMapper.toDTO(motoGravada);
		return ResponseEntity.status(HttpStatus.CREATED).body(motoResponseDTO);
	}

	// localhost:8089/motos
	@GetMapping
	public ResponseEntity<List<MotoResponseDTO>> buscarMotos() {
		List<Moto> motos = motoServico.buscarMotos();
		List<MotoResponseDTO> motosResponseDTO = motoMapper.toDTO(motos);
		return ResponseEntity.status(HttpStatus.OK).body(motosResponseDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarUmaMoto(@PathVariable(value = "id") Long id) throws MotoNotFoundException {
		Moto motoGravada = motoServico.buscarMotoPorId(id);
		MotoResponseDTO motoResponseDTO = motoMapper.toDTO(motoGravada);
		return ResponseEntity.status(HttpStatus.OK).body(motoResponseDTO);
	}
	
	@GetMapping("/dividir/{id}")
	public ResponseEntity<Object> buscarUm(@PathVariable(value = "id") Long id) throws MotoNotFoundException {
		int retorno = motoServico.retorno(id);
		return ResponseEntity.status(HttpStatus.OK).body(retorno);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> editar(@PathVariable(value = "id") Long id,
			@RequestBody MotoCreateDTO motoCreateDTO) {
		try {
			Moto moto = motoMapper.toEntity(motoCreateDTO);
			Moto motoGravada = motoServico.editarMoto(id, moto);
			MotoResponseDTO motoResponseDTO = motoMapper.toDTO(motoGravada);
			return ResponseEntity.status(HttpStatus.OK).body(motoResponseDTO);
		} catch (MotoNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletar(@PathVariable(value = "id") Long id) {
		try {
			motoServico.deletarMoto(id);
			return ResponseEntity.status(HttpStatus.OK).body("Moto removida com sucesso");
		} catch (MotoNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
