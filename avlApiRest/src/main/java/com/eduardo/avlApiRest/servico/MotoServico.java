package com.eduardo.avlApiRest.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.avlApiRest.excecao.MotoNotFoundException;
import com.eduardo.avlApiRest.modelo.Moto;
import com.eduardo.avlApiRest.repositorio.MotoRepositorio;

@Service
public class MotoServico{
	@Autowired
	private MotoRepositorio motoRepositorio;
	public Moto salvar(Moto moto) {
		return motoRepositorio.save(moto);
	}
	
	public List<Moto> buscarMotos(){
		return motoRepositorio.findAll();
		}
	
	public Moto buscarMotoPorId(Long id) throws MotoNotFoundException {
		Optional<Moto> opt = motoRepositorio.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new MotoNotFoundException("Moto com id " + id + " não existe!");
		}		
	}
	
	public int retorno(Long id) throws MotoNotFoundException {
		Optional<Moto> opt = motoRepositorio.findById(id);
		if (opt.isPresent()) {
			int x = opt.get().getKm();
			int y = opt.get().getCons();
			return x/y;
		} else {
			throw new MotoNotFoundException("Moto com id " + id + " não existe!");
		}		
	}
	
	public Moto editarMoto(Long id, Moto moto)throws MotoNotFoundException {
		Moto motoGravada = buscarMotoPorId(id);
		motoGravada.setMarca(moto.getMarca());
		motoGravada.setModelo(moto.getModelo());
		motoGravada.setCor(moto.getCor());
		motoGravada.setKm(moto.getKm());
		motoGravada.setCons(moto.getCons());
		return motoRepositorio.save(motoGravada);
	}

	public void deletarMoto(Long id) throws MotoNotFoundException{
		Moto moto = buscarMotoPorId(id);
		motoRepositorio.delete(moto);
	}

}


