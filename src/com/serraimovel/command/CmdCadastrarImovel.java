package com.serraimovel.command;

import java.io.IOException;

import javax.servlet.ServletException;

import com.serraimovel.excecao.SerraImovelException;
import com.serraimovel.bo.ProprietarioBO;

public class CmdCadastrarImovel extends CmdAbstrato {



	@Override
	public void processar() throws ServletException, IOException {
		
		String url="";
		try {
			
			ProprietarioBO bo = new ProprietarioBO();
			
			request.setAttribute("proprietarios", bo.buscarProprietarios());
			
			url = "/jsp/detalharImovel.jsp";
			
			
		} catch (SerraImovelException exc) {
			request.setAttribute("excecao", exc);
		}
		
		forward(url);

	}

}
