package com.serraimovel.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CmdAbstrato {
	
	protected ServletContext contexto;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public void init(ServletContext contexto, HttpServletRequest request,
			HttpServletResponse response) {
		this.contexto = contexto;
		this.request = request;
		this.response = response;
	}
	
	public abstract void processar() throws ServletException, IOException;
	
	protected void forward(String alvo) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(alvo);
		dispatcher.forward(request, response);
	}

}
