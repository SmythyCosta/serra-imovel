package com.serraimovel.bo;

import java.util.List;

import com.serraimovel.dao.ProprietarioDAO;
import com.serraimovel.dto.PessoaFisica;
import com.serraimovel.excecao.SerraImovelException;

public class ProprietarioBO {

	public void gravarPROPRIETARIO(PessoaFisica pessoa) throws SerraImovelException {

		ProprietarioDAO dao = new ProprietarioDAO();

		if (pessoa.getId() == 0) {
			dao.inserir(pessoa);
		} else {
			dao.atualizar(pessoa);
		}

	}

	public PessoaFisica buscarPorProprietario(Integer funcionario)
			throws SerraImovelException {
		
		ProprietarioDAO dao = new ProprietarioDAO();		
		
		PessoaFisica pessoaFisica = dao.buscarPorProprietario(funcionario);
		
		return pessoaFisica;
	}
	
	public Integer excluirPorProprietario(Integer funcionario)
			throws SerraImovelException {
		
		ProprietarioDAO dao = new ProprietarioDAO();		
		
		Integer pessoaFisica = dao.excluirPorProprietarioGeral(funcionario);
		
		return pessoaFisica;
	}

	public List<PessoaFisica> buscarProprietarios() throws SerraImovelException {

		ProprietarioDAO dao = new ProprietarioDAO();

		List<PessoaFisica> lstProprietarios = dao.buscarProprietarios();

		return lstProprietarios;
	}

}
