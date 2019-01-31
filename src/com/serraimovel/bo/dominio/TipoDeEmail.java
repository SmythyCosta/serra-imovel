package com.serraimovel.bo.dominio;

import java.util.HashMap;
import java.util.Map;

public enum TipoDeEmail {

	PESSOAL(1, "Pessoal"), COMERCIAL(2, "Comercial");

	private final int valor;
	private final String nome;

	private static final Map<Integer, TipoDeEmail> intToEnum = 
		new HashMap<Integer, TipoDeEmail>();

	TipoDeEmail(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	static {
		for (TipoDeEmail tipo : values()) {
			intToEnum.put(tipo.valor, tipo);
		}
	}

	public int getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public static TipoDeEmail fromInt(int valor) {
		return intToEnum.get(valor);
	}

}
