package com.serraimovel.dto;

public class Pessoa extends ObjetoDoDominio {
	
	private String nome;
	private Email email;
	private Telefone telefone;

	public Pessoa() {
		super();
		email = new Email();
		telefone = new Telefone();
	}

	public Pessoa(long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

}
