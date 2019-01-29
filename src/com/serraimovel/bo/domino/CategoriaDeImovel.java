package com.serraimovel.bo.domino;

import java.util.HashMap;
import java.util.Map;

public enum CategoriaDeImovel {
	
	APARTAMENTO(1, "Apartamento"), 
	CASA(2, "Casa"), 
	CHACARA(3,"Chácara"),
	KITNET(4,"Kitnet"),
	LOJA(5,"Loja"),
	MANSAO(6,"Mansão");
	
	private final int valor;
	private final String nome;
	
	private static final Map<Integer, CategoriaDeImovel> intToEnum = new HashMap<Integer, CategoriaDeImovel>();

	private CategoriaDeImovel(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}
	
	static {
		for (CategoriaDeImovel tipo: values()){
			intToEnum.put(tipo.valor, tipo);
		}
	}
	
	public int getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}
	
	public static CategoriaDeImovel fromInt(int valor) {
		return intToEnum.get(valor);
	}
	
	

}
