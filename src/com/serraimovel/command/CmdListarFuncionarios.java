package com.serraimovel.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.serraimovel.bo.FuncionarioBO;
import com.serraimovel.dto.PessoaFisica;
import com.serraimovel.excecao.SerraImovelException;

public class CmdListarFuncionarios extends CmdAbstrato {

	@Override
	public void processar() throws ServletException, IOException {

		String url = null;
		try {
		
			FuncionarioBO bo = new FuncionarioBO();
			
			List<PessoaFisica> lstFuncionarios = bo.buscarFuncionarios();
			
			HttpSession sessao = request.getSession();
			
			sessao.setAttribute("funcionarios", lstFuncionarios);
			
			url = "/jsp/listarFuncionarios.jsp";
		
		} catch (SerraImovelException exc) {
			request.setAttribute("excecao", exc);
			url = "/jsp/errorPage.jsp";
		}
		
		forward(url);

	}

}
