package com.serraimovel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.serraimovel.bo.domino.CategoriaDeImovel;
import com.serraimovel.bo.domino.SituacaoDeImovel;
import com.serraimovel.bo.domino.TipoDeImovel;
import com.serraimovel.dto.Imovel;
import com.serraimovel.excecao.SerraImovelException;

public class ImovelDAO {

	private final String SQL_INSERIRIMOVEL = 
			"INSERT INTO imovel " +
			"(id_imovel, " +
			"tipo, " +
			"categoria, " +
			"situacao, " +
			"nome, " +
			"descricao, " +
			"local, " +
			"cidade, " +
			"uf, " +
			"areaTotal, " +
			"areaConstruida, " +
			"preco, " +
			"img, " +
			"id_proprietario) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	
	private final String SQL_EXCLUIRIMOVEL = 
			"DELETE FROM imovel " +
			"WHERE id_imovel = ?";
	
	private final String SQL_BUSCARIMOVEIS = 
			"SELECT " +
			"i.id_imovel, " +
			"i.tipo, " +
			"i.categoria, " +
			"i.situacao, " +
			"i.nome, " +
			"i.descricao, " +
			"i.local, " +
			"i.cidade, " +
			"i.uf, " +
			"i.areaTotal, " +
			"i.areaConstruida, " +
			"i.preco, " +
			"i.img, " +
			"i.id_proprietario " 
			+ "FROM "
			+ "imovel i, proprietario p " 
			+ "WHERE "
			+ "i.id_proprietario = p.id_proprietario "
			+ " ";

	private final String SQL_ATUALIZARIMOVEL = 
			"UPDATE imovel SET " +
			"tipo = ?, " +
			"categoria = ?, " +
			"situacao = ?, " +
			"nome = ?, " +
			"descricao = ?, " +
			"local = ?, " +
			"cidade = ?, " +
			"uf = ?, " +
			"areaTotal = ?, " +
			"areaConstruida = ?, " +
			"preco = ?, " +
			"img = ?, " +
			"id_proprietario = ? "
			+ "WHERE id_imovel = ?";

	private final String SQL_BUSCARPORIMOVEL = 
			"SELECT " +
			"i.id_imovel, " +
			"i.tipo, " +
			"i.categoria, " +
			"i.situacao, " +
			"i.nome, " +
			"i.descricao, " +
			"i.local, " +
			"i.cidade, " +
			"i.uf, " +
			"i.areaTotal, " +
			"i.areaConstruida, " +
			"i.preco, " +
			"i.img, " +
			"i.id_proprietario " 
			+ "FROM "
			+ "imovel i, proprietario p " 
			+ "WHERE i.id_imovel = ? "
			+ "AND i.id_proprietario = p.id_proprietario "
			+ " ";

	public void inserir(Imovel imovel) throws SerraImovelException {

		ServiceLocator pool = ServiceLocator.getInstance();
		Connection con = pool.getConnection();
		PreparedStatement stmt = null;

		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(SQL_INSERIRIMOVEL);

			GeradorDeChave geradorDeChave = new GeradorDeChave("imovel");

			long codigo = geradorDeChave.getProximoCodigo();

			stmt.setLong(1, codigo);
			stmt.setInt(2, imovel.getTipoDeImovel().getValor());
			stmt.setInt(3, imovel.getCategoriaDeImovel().getValor());
			stmt.setInt(4, imovel.getSituacaoDeImovel().getValor());
			stmt.setString(5, imovel.getNome());
			stmt.setString(6, imovel.getDescricao());
			stmt.setString(7, imovel.getLocal());
			stmt.setString(8, imovel.getCidade());
			stmt.setString(9, imovel.getUf());
			stmt.setBigDecimal(10, imovel.getAreaTotal());
			stmt.setBigDecimal(11, imovel.getAreaConstruida());
			stmt.setBigDecimal(12, imovel.getPreco());
			stmt.setString(13, imovel.getImg());
			stmt.setLong(14, imovel.getIdProprietario());

			stmt.executeUpdate();

		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer("N&#227;o foi poss&#237;vel realizar a grava&#231;&#227;o.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		} finally {
			pool.closeConnection(con, stmt);
		}
	}

	public List<Imovel> buscarImovels() throws SerraImovelException {

		ServiceLocator serviceLocator = ServiceLocator.getInstance();

		Connection con = serviceLocator.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			stmt = con.prepareStatement(SQL_BUSCARIMOVEIS);
			rs = stmt.executeQuery();

			List<Imovel> lstImoveis = new ArrayList<Imovel>();

			while (rs.next()) {
				Imovel imo = criarImovel(rs);
				lstImoveis.add(imo);
			}

			return lstImoveis;

		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer("N&#227;o foi poss&#237;vel buscar os Im&oacute;veis.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		} finally {
			serviceLocator.closeConnection(con, stmt, rs);
		}
	}

	private Imovel criarImovel(ResultSet rs) throws SQLException {

		Imovel imovel = new Imovel();

		imovel.setId(rs.getLong("id_imovel"));
		imovel.setTipoDeImovel(TipoDeImovel.fromInt(rs.getInt("tipo")));
		imovel.setCategoriaDeImovel(CategoriaDeImovel.fromInt(rs.getInt("categoria")));
		imovel.setSituacaoDeImovel(SituacaoDeImovel.fromInt(rs.getInt("situacao")));
		imovel.setNome(rs.getString("nome"));
		imovel.setDescricao(rs.getString("descricao"));
		imovel.setLocal(rs.getString("local"));
		imovel.setCidade(rs.getString("cidade"));
		imovel.setUf(rs.getString("uf"));
		imovel.setAreaTotal(rs.getBigDecimal("areaTotal"));
		imovel.setAreaConstruida(rs.getBigDecimal("areaConstruida"));
		imovel.setPreco(rs.getBigDecimal("preco"));
		imovel.setImg(rs.getString("img"));
		imovel.setIdProprietario(rs.getLong("id_proprietario"));

		return imovel;
	}

	public Imovel buscarPorImovel(Integer usuario) throws SerraImovelException {

		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection con = serviceLocator.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			stmt = con.prepareStatement(SQL_BUSCARPORIMOVEL);
			stmt.setInt(1, usuario);
			rs = stmt.executeQuery();

			Imovel imovel = new Imovel();

			while (rs.next()) {

				imovel.setId(rs.getLong("id_imovel"));
				imovel.setTipoDeImovel(TipoDeImovel.fromInt(rs.getInt("tipo")));
				imovel.setCategoriaDeImovel(CategoriaDeImovel.fromInt(rs.getInt("categoria")));
				imovel.setSituacaoDeImovel(SituacaoDeImovel.fromInt(rs.getInt("situacao")));
				imovel.setNome(rs.getString("nome"));
				imovel.setDescricao(rs.getString("descricao"));
				imovel.setLocal(rs.getString("local"));
				imovel.setCidade(rs.getString("cidade"));
				imovel.setUf(rs.getString("uf"));
				imovel.setAreaTotal(rs.getBigDecimal("areaTotal"));
				imovel.setAreaConstruida(rs.getBigDecimal("areaConstruida"));
				imovel.setPreco(rs.getBigDecimal("preco"));
				imovel.setImg(rs.getString("img"));
				imovel.setIdProprietario(rs.getLong("id_proprietario"));

			}

			return imovel;

		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer("N&#227;o foi poss&#237;vel buscar o Im&oacute;vel.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		} finally {
			serviceLocator.closeConnection(con, stmt, rs);
		}
	}

	public void atualizar(Imovel imovel) throws SerraImovelException {
		
		ServiceLocator pool = ServiceLocator.getInstance();
		Connection con = pool.getConnection();
		PreparedStatement stmt = null;
		
		try {

			con.setAutoCommit(false);

			stmt = con.prepareStatement(SQL_ATUALIZARIMOVEL);

			stmt.setInt(1, imovel.getTipoDeImovel().getValor());
			stmt.setInt(2, imovel.getCategoriaDeImovel().getValor());
			stmt.setInt(3, imovel.getSituacaoDeImovel().getValor());
			stmt.setString(4, imovel.getNome());
			stmt.setString(5, imovel.getDescricao());
			stmt.setString(6, imovel.getLocal());
			stmt.setString(7, imovel.getCidade());
			stmt.setString(8, imovel.getUf());
			stmt.setBigDecimal(9, imovel.getAreaTotal());
			stmt.setBigDecimal(10, imovel.getAreaConstruida());
			stmt.setBigDecimal(11, imovel.getPreco());
			stmt.setString(12, imovel.getImg());
			stmt.setLong(13, imovel.getIdProprietario());
			stmt.setLong(14, imovel.getId());

			stmt.executeUpdate();

		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer("N&#227;o foi poss&#237;vel realizar a grava&#231;&#227;o.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		} finally {
			pool.closeConnection(con, stmt);
		}
	}

	//
	private void excluirImovel(Connection con, PreparedStatement stmt, long id_imovel) throws SQLException {

		stmt = con.prepareStatement(SQL_EXCLUIRIMOVEL);
		stmt.setLong(1, id_imovel);
		stmt.executeUpdate();
	}

	public Integer excluirPorImovelGeral(Integer imovel) throws SerraImovelException {
		
		ServiceLocator pool = ServiceLocator.getInstance();
		Connection con = pool.getConnection();
		PreparedStatement stmt = null;
		
		try {

			con.setAutoCommit(false);

			this.excluirImovel(con, stmt, imovel);

			con.commit();

		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer("N&#227;o foi poss&#237;vel realizar a exclus&#231;&#227;o.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		} finally {
			pool.closeConnection(con, stmt);
		}
		return imovel;
	}
}
