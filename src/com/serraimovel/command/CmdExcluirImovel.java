package com.serraimovel.command;

import java.io.IOException;

import javax.servlet.ServletException;

import com.serraimovel.excecao.SerraImovelException;



public class CmdExcluirImovel extends CmdAbstrato {

	@Override
	public void processar() throws ServletException, IOException {

		String url = null;
		try {
		
			ImovelBO bo = new ImovelBO();
			
			Integer imo = Integer.parseInt(request.getParameter("id_imovel"));
						
			Integer imovel = bo.excluirPorImovel(imo);
					
			request.setAttribute("imovel", imovel);
			
			url = "controller?cmd=ListarImoveis";
		
		} catch (SerraImovelException exc) {
			request.setAttribute("excecao", exc);
			url = "/jsp/errorPage.jsp";
		} finally {
		}
		
		forward(url);

	}

}
