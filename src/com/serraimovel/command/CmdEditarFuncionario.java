package com.serraimovel.command;

import java.io.IOException;

import javax.servlet.ServletException;

import com.serraimovel.bo.FuncionarioBO;
import com.serraimovel.dto.PessoaFisica;
import com.serraimovel.excecao.SerraImovelException;

public class CmdEditarFuncionario extends CmdAbstrato {
	
	@Override
	public void processar() throws ServletException, IOException {

		String url = null;
		try {
		
			FuncionarioBO bo = new FuncionarioBO();
			
			Integer func = Integer.parseInt(request.getParameter("id_funcionario"));
						
			PessoaFisica funcionario = bo.buscarPorFuncionario(func);
						
			//HttpSession sessao = request.getSession();
			
			//sessao.setAttribute("funcionario", funcionario);
			
			request.setAttribute("funcionario", funcionario);
			
			url = "/jsp/detalharFuncionario.jsp";
		
		} catch (SerraImovelException exc) {
			request.setAttribute("excecao", exc);
			url = "/jsp/errorPage.jsp";
		}
		
		forward(url);
		
	}

}
