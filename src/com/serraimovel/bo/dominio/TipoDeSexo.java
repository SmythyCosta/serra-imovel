package com.serraimovel.bo.dominio;

import java.util.HashMap;
import java.util.Map;

public enum TipoDeSexo {

	FEMININO(1, "Feminino"), MASCULINO(2, "Masculino");

	private final int valor;
	private final String nome;

	private static final Map<Integer, TipoDeSexo> intToEnum = new HashMap<Integer, TipoDeSexo>();

	TipoDeSexo(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	static {
		for (TipoDeSexo tipo : values()) {
			intToEnum.put(tipo.valor, tipo);
		}
	}

	public int getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public static TipoDeSexo fromInt(int valor) {
		return intToEnum.get(valor);
	}

}
