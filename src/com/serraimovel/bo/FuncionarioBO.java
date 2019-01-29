package com.serraimovel.bo;

import java.util.List;

import com.serraimovel.dao.FuncionarioDAO;
import com.serraimovel.dto.PessoaFisica;
import com.serraimovel.excecao.SerraImovelException;

public class FuncionarioBO {
	
	public void gravarFUNC(PessoaFisica pessoa) throws SerraImovelException {

		FuncionarioDAO dao = new FuncionarioDAO();

		if (pessoa.getId() == 0) {
			dao.inserir(pessoa);
		} else {
			dao.atualizar(pessoa);
		}

	}
	
	public PessoaFisica buscarPorFuncionario(Integer funcionario)
			throws SerraImovelException {
		
		FuncionarioDAO dao = new FuncionarioDAO();		
		
		PessoaFisica pessoaFisica = dao.buscarPorFuncionario(funcionario);
		
		return pessoaFisica;
	}
	
	public Integer excluirPorFuncionario(Integer funcionario)
			throws SerraImovelException {
		
		FuncionarioDAO dao = new FuncionarioDAO();		
		
		Integer pessoaFisica = dao.excluirPorFuncionarioGeral(funcionario);
		
		return pessoaFisica;
	}
	
	public List<PessoaFisica> buscarFuncionarios() throws SerraImovelException {

		FuncionarioDAO dao = new FuncionarioDAO();

		List<PessoaFisica> lstFuncionarios = dao.buscarFuncionarios();

		return lstFuncionarios;
	}

}
