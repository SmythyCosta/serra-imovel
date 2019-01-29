package com.serraimovel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.serraimovel.excecao.SerraImovelException;

public class GeradorDeChave {

	private static final byte INCREMENTO = 1;
	private ServiceLocator pool;
	private Connection con;
	private String tabela;
	private long proximoCodigo;
	private long maximoCodigo;
	private String sql;

	/*
	 * 
	 * obtains a connection pool connection, initializes 
	 * instance variables and changes autoCommit to false.
	 * 
	 * */
	public GeradorDeChave(String tabela) throws SerraImovelException {
		pool = ServiceLocator.getInstance();
		con = pool.getConnection();
		this.tabela = tabela;
		proximoCodigo = 0;
		maximoCodigo = 0;
		try {
			con.setAutoCommit(false);
		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer("N&#227;o foi poss&#237;vel realizar a opera&#231;&#227;o.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		}
	}
	
	
	/*
	 * 
	 * This method retrieves and returns the next code (id) 
	 * to be assigned to the record being written.
	 * 
	 * */
	public synchronized long getProximoCodigo() throws SerraImovelException {
		if (proximoCodigo == maximoCodigo) {
			reservarCodigo();
		}
		return proximoCodigo;
	}
	
	/*
	 * 
	 * This method gets the next code (id) from the key table, 
	 * saves it, increments it, and writes it back
	 * 
	 * */
	private void reservarCodigo() throws SerraImovelException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		long proximoCodigoNovo;
		try {
			sql = "SELECT proximoCodigo FROM chaves WHERE tabela = ? FOR UPDATE";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tabela);
			rs = stmt.executeQuery();
			rs.next();
			proximoCodigoNovo = rs.getLong("proximoCodigo");
		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer(
					"N&#227;o foi poss&#237;vel realizar a opera&#231;&#227;o.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		}

		long maximoCodigoNovo = proximoCodigoNovo + INCREMENTO;
		stmt = null;
		rs = null;
		try {
			sql = "UPDATE chaves SET proximoCodigo = ? WHERE tabela = ?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, maximoCodigoNovo);
			stmt.setString(2, tabela);
			stmt.executeUpdate();
			con.commit();
			proximoCodigo = proximoCodigoNovo;
			maximoCodigo = maximoCodigoNovo;
		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer(
					"N&#227;o foi poss&#237;vel realizar a opera&#231;&#227;o.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		} finally {
			pool.closeConnection(con, stmt, rs);
		}
	}

}
