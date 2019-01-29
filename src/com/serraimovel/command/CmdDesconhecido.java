package com.serraimovel.command;

import java.io.IOException;

import javax.servlet.ServletException;

public class CmdDesconhecido extends CmdAbstrato {
	
	/*
	 * 
	 * These instructions redirect the user to the error404.jsp page,
	 * if an unknown command is submitted.
	 * 
	 * */
	@Override
	public void processar() throws ServletException, IOException {
		String url = "/jsp/error404.jsp";
		forward(url);
	}
	

}
