package com.serraimovel.command;

import java.io.IOException;

import javax.servlet.ServletException;

import com.serraimovel.bo.FuncionarioBO;
import com.serraimovel.excecao.SerraImovelException;

public class CmdExcluirFuncionario extends CmdAbstrato{

	@Override
	public void processar() throws ServletException, IOException {

		String url = null;
		try {
		
			FuncionarioBO bo = new FuncionarioBO();
			
			Integer func = Integer.parseInt(request.getParameter("id_funcionario"));
						
			Integer funcionario = bo.excluirPorFuncionario(func);
						
			//HttpSession seas = request.getSession();
			
			//sessao.setAttribute("funcionario", funcionario);
			
			request.setAttribute("funcionario", funcionario);
			
			url = "controller?cmd=ListarFuncionarios";
		
		} catch (SerraImovelException exc) {
			request.setAttribute("excecao", exc);
			url = "/jsp/errorPage.jsp";
		} finally {
		}
		
		forward(url);

	}
	
}
