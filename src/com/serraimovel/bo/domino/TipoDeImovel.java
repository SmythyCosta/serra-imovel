package com.serraimovel.bo.domino;

import java.util.HashMap;
import java.util.Map;

public enum TipoDeImovel {

	ALUGUEL(1, "Aluguel"), 
	COMPRA(2, "Compra"), 
	VENDA(3,"Venda");
	
	private final int valor;
	private final String nome;

	private static final Map<Integer, TipoDeImovel> intToEnum = new HashMap<Integer, TipoDeImovel>();

	TipoDeImovel(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	static {
		for (TipoDeImovel tipo : values()) {
			intToEnum.put(tipo.valor, tipo);
		}
	}

	public int getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public static TipoDeImovel fromInt(int valor) {
		return intToEnum.get(valor);
	}

}
