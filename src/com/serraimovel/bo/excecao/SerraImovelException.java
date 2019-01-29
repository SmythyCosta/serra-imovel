package com.serraimovel.bo.excecao;

public class SerraImovelException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -869798864741762272L;
	
	public SerraImovelException(String mensagem, Throwable causa) {
		super(mensagem, causa);
		
		causa.printStackTrace();
		
	}
	
	public SerraImovelException(String mensagem) {
		super(mensagem);
		// TODO Auto-generated constructor stub
	}
	
}
