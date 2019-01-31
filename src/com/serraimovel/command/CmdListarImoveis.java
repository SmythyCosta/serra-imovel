package com.serraimovel.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.serraimovel.dto.Imovel;
import com.serraimovel.excecao.SerraImovelException;
import com.serraimovel.bo.ImovelBO;

public class CmdListarImoveis extends CmdAbstrato {

	@Override
	public void processar() throws ServletException, IOException {

		String url = null;
		try {
		
			ImovelBO bo = new ImovelBO();
					
			List<Imovel> lstImoveis = bo.buscarImovels();
			
			HttpSession sessao = request.getSession();
			
			sessao.setAttribute("imoveis", lstImoveis);
			
			url = "/jsp/listarImoveis.jsp";
		
		} catch (SerraImovelException exc) {
			request.setAttribute("excecao", exc);
			url = "/jsp/errorPage.jsp";
		}
		
		forward(url);

	}

}
