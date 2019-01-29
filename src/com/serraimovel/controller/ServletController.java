package com.serraimovel.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.serraimovel.command.CmdAbstrato;
import com.serraimovel.command.CmdDesconhecido;
import com.serraimovel.excecao.SerraImovelException;

@WebServlet("/jsp/controller")
public class ServletController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public ServletController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processarRequisicao(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processarRequisicao(request, response);
	}
	
	private void processarRequisicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CmdAbstrato comando;
		
		try {

			comando = obterComando(request);
			comando.init(this.getServletContext(), request, response);
			comando.processar();

		} catch (SerraImovelException exc) {
			request.setAttribute("excecao", exc);
			String url = "/jsp/errorPage.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);

			dispatcher.forward(request, response);
		}
	}
	
	private CmdAbstrato obterComando(HttpServletRequest request)throws SerraImovelException {
		
		CmdAbstrato comando = null;
		
		try {
			comando = (CmdAbstrato) obterClasseDoComando(request).newInstance();
		} catch (Exception exc) {
			StringBuffer mensagem = new StringBuffer(
					"N&#227;o foi poss&#237;vel realizara opera&#231;&#227;o.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		}
		return comando;
	}

	/*
	 * 
	 * This method tries to get the command class by concatenation.
	 * 
	 * */
	private Class<?> obterClasseDoComando(HttpServletRequest request) {
		
		String nomeDaClasseDoComando = "com.pereirati.serraimovel.command."
				+ "Cmd" + request.getParameter("cmd");
		
		Class<?> classe = null;
		
		try {
			classe = Class.forName(nomeDaClasseDoComando);
		} catch (ClassNotFoundException exc) {
			classe = CmdDesconhecido.class;
		}
		return classe;
	}

}
