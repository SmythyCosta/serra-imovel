package com.serraimovel.bo.domino;

import java.util.HashMap;
import java.util.Map;

public enum SituacaoDeImovel {
	
	DISPONIVEL(1, "Disponível"), 
	INDISPONIVEL(2, "Indisponível"), 
	RESERVADO(3, "Reservado");
	
	private final int valor;
	private final String nome;
	
	private static final Map<Integer, SituacaoDeImovel> intToEnum = new HashMap<Integer, SituacaoDeImovel>();

	SituacaoDeImovel(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	static {
		for (SituacaoDeImovel tipo : values()) {
			intToEnum.put(tipo.valor, tipo);
		}
	}

	public int getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public static SituacaoDeImovel fromInt(int valor) {
		return intToEnum.get(valor);
	}

}
