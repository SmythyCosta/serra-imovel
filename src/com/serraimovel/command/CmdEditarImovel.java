package com.serraimovel.command;

import java.io.IOException;

import javax.servlet.ServletException;

import com.serraimovel.dto.Imovel;
import com.serraimovel.excecao.SerraImovelException;


public class CmdEditarImovel extends CmdAbstrato {

	@Override
	public void processar() throws ServletException, IOException {

		String url = null;
		try {
		
			ImovelBO bo = new ImovelBO();
			
			Integer imo = Integer.parseInt(request.getParameter("id_imovel"));
						
			Imovel imovel = bo.buscarPorImovel(imo);
						
			request.setAttribute("imovel", imovel);
			
			ProprietarioBO boProp = new ProprietarioBO();
			
			request.setAttribute("proprietarios", boProp.buscarProprietarios());
			
			
			url = "/jsp/detalharImovel.jsp";
		
		} catch (SerraImovelException exc) {
			request.setAttribute("excecao", exc);
			url = "/jsp/errorPage.jsp";
		}
		
		forward(url);

	}

}
