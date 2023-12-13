package com.eduardo.avlApiRest.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.avlApiRest.modelo.Moto;

public interface MotoRepositorio extends JpaRepository<Moto, Long> {

}
