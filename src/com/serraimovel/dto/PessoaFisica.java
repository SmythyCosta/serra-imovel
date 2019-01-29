package com.serraimovel.dto;

import java.util.Date;

import com.serraimovel.bo.domino.TipoDeSexo;


public class PessoaFisica extends Pessoa {
	
	private TipoDeSexo sexo;
	private Date dataNascimento;
	private Credencial credencial; //associação da classe Crendencial
	
	
	public PessoaFisica() {
		super();
		credencial = new Credencial();
		//pessoa = new Pessoa();
		//pessoa.getNome();
	}

	public PessoaFisica(long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public TipoDeSexo getSexo() {
		return sexo;
	}

	public void setSexo(TipoDeSexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Credencial getCredencial() {
		return credencial;
	}

	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;
	}

	
	
}