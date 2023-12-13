package com.eduardo.avlApiRest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MotoCreateDTO {
	@NotBlank(message = "A marca deve ser informada!")
	@Size(min = 2, message = "A marca deve ter no mínimo 2 caracteres")
	private String marca;
	
	@NotBlank(message = "O modelo deve ser informado!")
	@Size(min = 2, message = "O modelo deve ter no mínimo 2 caracteres.")
	private String modelo;
	
	@NotBlank(message = "A cor deve ser informada!")
	@Size(min = 3, message = "A cor deve ter no mínimo 3 caracteres.")
	private String cor;
	
	@Max(value = 999999, message = "A kilometragem não deve ser maior que 999999")
	private int km;
	
	private int cons;

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public int getCons() {
		return cons;
	}

	public void setCons(int cons) {
		this.cons = cons;
	}
	
	}