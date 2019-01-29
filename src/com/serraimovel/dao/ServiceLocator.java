package com.serraimovel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.serraimovel.excecao.SerraImovelException;

public class ServiceLocator {

	private static ServiceLocator pool = null;
	private static DataSource dataSource = null;

	private ServiceLocator() throws SerraImovelException {

		InitialContext ic = null;

		try {

			ic = new InitialContext();
			dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/serraimovel");

		} catch (NamingException exc) {
			StringBuffer mensagem = new StringBuffer(
					"N&#227;o foi poss&#237;vel estabelecer conex&#227;o com o banco de dados.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		}
	}

	/*
	 * 
	 * Because the constructor is private, 
	 * this method is required to create and return an instance of ServiceLocator.
	 * 
	 * */
	synchronized static ServiceLocator getInstance() throws SerraImovelException {
		if (pool == null) {
			pool = new ServiceLocator();
		}
		return pool;
	}

	Connection getConnection() throws SerraImovelException {
		try {

			return dataSource.getConnection();

		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer(
					"N&#227;o foi poss&#237;vel estabelecer conex&#227;o com o banco de dados.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		}
	}

	void closeConnection(Connection con) throws SerraImovelException {
		closeConnection(con, null, null);
	}

	void closeConnection(Connection con, PreparedStatement stmt) throws SerraImovelException {
		closeConnection(con, stmt, null);
	}

	void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) throws SerraImovelException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer("Não foi possível finalizar a conexão com banco	de dados.");
			mensagem.append("\nMotivo: " + exc.getMessage());
			throw new SerraImovelException(mensagem.toString(), exc);
		}
	}
}
