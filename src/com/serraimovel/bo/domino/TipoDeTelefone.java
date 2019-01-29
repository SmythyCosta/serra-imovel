package com.serraimovel.bo.domino;


import java.util.HashMap;
import java.util.Map;

public enum TipoDeTelefone {

	CELULAR(1, "Celular"), COMERCIAL(2, "Comercial"), RESIDENCIAL(3,
			"Residencial");

	private final int valor;
	private final String nome;

	private static final Map<Integer, TipoDeTelefone> intToEnum = new HashMap<Integer, TipoDeTelefone>();

	TipoDeTelefone(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	static {
		for (TipoDeTelefone tipo : values()) {
			intToEnum.put(tipo.valor, tipo);
		}
	}

	public int getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public static TipoDeTelefone fromInt(int valor) {
		return intToEnum.get(valor);
	}

}
