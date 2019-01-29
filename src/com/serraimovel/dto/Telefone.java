package com.serraimovel.dto;

import com.serraimovel.bo.domino.TipoDeTelefone;

public class Telefone {
	
	private TipoDeTelefone tipo;
	private int ddd;
	private int numero;

	public Telefone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoDeTelefone getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeTelefone tipo) {
		this.tipo = tipo;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
