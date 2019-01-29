package com.serraimovel.dto;

import com.serraimovel.bo.domino.TipoDeEmail;

public class Email {
	
	private TipoDeEmail tipo;
	private String endereco;
	
	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoDeEmail getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeEmail tipo) {
		this.tipo = tipo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
