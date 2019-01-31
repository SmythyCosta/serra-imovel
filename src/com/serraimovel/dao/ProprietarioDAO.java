package com.serraimovel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.serraimovel.bo.dominio.TipoDeEmail;
import com.serraimovel.bo.dominio.TipoDeSexo;
import com.serraimovel.bo.dominio.TipoDeTelefone;
import com.serraimovel.dto.Email;
import com.serraimovel.dto.PessoaFisica;
import com.serraimovel.dto.Telefone;
import com.serraimovel.excecao.SerraImovelException;
import com.serraimovel.util.DataUtils;

public class ProprietarioDAO {

	private final String SQL_INSERIRPROPRIETARIO = 
			"INSERT INTO proprietario " +
			"(id_proprietario, nome, sexo, data_nascimento) " +
			"VALUES (?, ?, ?, ?)";
	
	private final String SQL_EXCLUIRPROPRIETARIO = 
			"DELETE FROM proprietario " +
			"WHERE id_proprietario = ?";
	
	private final String SQL_INSERIREMAILPROPRIETARIO = 
			"INSERT INTO email_proprietario " +
			"(seql_email, tipo, endereco, id_proprietario) " +
			"VALUES (?, ?, ?, ?)";
	
	private final String SQL_EXCLUIREMAILPROPRIETARIO = 
			"DELETE FROM email_proprietario " +
			"WHERE id_proprietario = ?";
	
	private final String SQL_INSERIRTELEFONEPROPRIETARIO = 
			"INSERT INTO telefone_proprietario " +
			"(seql_telefone, tipo, ddd, numero, id_proprietario) " +
			"VALUES (?, ?, ?, ?, ?)";
	
	private final String SQL_EXCLUIRTELEFONEPROPRIETARIO = 
			"DELETE FROM telefone_proprietario " +
			"WHERE id_proprietario = ?";
	
	private final short UM = 1;

	private final String SQL_BUSCARPROPRIETARIOS = 
			"SELECT "
			+ "p.id_proprietario, " 
			+ "p.nome, " 
			+ "p.sexo, "
			+ "p.data_nascimento, " 
			+ "ep.tipo, " 
			+ "ep.endereco, "
			+ "tp.tipo, " 
			+ "tp.ddd, " 
			+ "tp.numero " 
			+ "FROM "
			+ "proprietario p, " 
			+ "email_proprietario ep, "
			+ "telefone_proprietario tp " 
			+ "WHERE "
			+ "ep.id_proprietario = p.id_proprietario "
			+ "AND tp.id_proprietario = p.id_proprietario ";

	private final String SQL_ATUALIZARPROPRIETARIO = 
			"UPDATE proprietario SET "
			+ "nome = ?, " 
			+ "sexo = ?, " 
			+ "data_nascimento = ? "
			+ "WHERE id_proprietario = ?";

	private final String SQL_BUSCARPORPROPRIETARIO = 
			"SELECT "
			+ "p.id_proprietario, "
			+ "p.nome, "
			+ "p.sexo, "
			+ "p.data_nascimento, "
			+ "e.tipo tipoEmail, "
			+ "e.endereco enderecoEmail, "
			+ "t.tipo tipoTelefone, "
			+ "t.ddd dddTelefone, "
			+ "t.numero numeroTelefone "			
			+ "FROM	"
			+ "proprietario p, " 
			+ "email_proprietario e, " 
			+ "telefone_proprietario t "
			+ "WHERE p.id_proprietario = ? "
			+ "AND p.id_proprietario = e.id_proprietario "
			+ "AND p.id_proprietario =	t.id_proprietario";

	public void inserir(PessoaFisica pessoa) throws SerraImovelException {

		ServiceLocator pool = ServiceLocator.getInstance();
		Connection con = pool.getConnection();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(SQL_INSERIRPROPRIETARIO);

			GeradorDeChave geradorDeChave = new GeradorDeChave("proprietario");

			long codigo = geradorDeChave.getProximoCodigo();

			stmt.setLong(1, codigo);
			stmt.setString(2, pessoa.getNome());
			stmt.setInt(3, pessoa.getSexo().getValor());
			java.util.Date dataNascimento = pessoa.getDataNascimento();
			stmt.setDate(4, DataUtils.utilDateToSqlDate(dataNascimento));

			stmt.executeUpdate();

			this.gravarEmail(con, stmt, codigo, pessoa.getEmail());

			this.gravarTelefone(con, stmt, codigo, pessoa.getTelefone());

			
			con.commit();

		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer(
					"N&#227;o foi poss&#237;vel realizar a grava&#231;&#227;o.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		} finally {
			pool.closeConnection(con, stmt);
		}
	}

	private void gravarEmail(Connection con, PreparedStatement stmt, long id_proprietario, Email email) throws SQLException {
		this.excluirEmail(con, stmt, id_proprietario);
		this.inserirEmail(con, stmt, id_proprietario, email);
	}

	private void excluirEmail(Connection con, PreparedStatement stmt, long id_proprietario) throws SQLException {
		stmt = con.prepareStatement(SQL_EXCLUIREMAILPROPRIETARIO);
		stmt.setLong(1, id_proprietario);
		stmt.executeUpdate();
	}	

	private void inserirEmail(Connection con, PreparedStatement stmt, long id_proprietario, Email email) throws SQLException {
		stmt = con.prepareStatement(SQL_INSERIREMAILPROPRIETARIO);
		stmt.setShort(1, UM);
		stmt.setInt(2, email.getTipo().getValor());
		stmt.setString(3, email.getEndereco());
		stmt.setLong(4, id_proprietario);
		stmt.executeUpdate();

	}

	private void gravarTelefone(Connection con, PreparedStatement stmt, long id_proprietario, Telefone telefone) throws SQLException {
		this.excluirTelefone(con, stmt, id_proprietario);
		this.inserirTelefone(con, stmt, id_proprietario, telefone);
	}

	private void excluirTelefone(Connection con, PreparedStatement stmt, long id_proprietario) throws SQLException {
		stmt = con.prepareStatement(SQL_EXCLUIRTELEFONEPROPRIETARIO);
		stmt.setLong(1, id_proprietario);
		stmt.executeUpdate();
	}	

	private void inserirTelefone(Connection con, PreparedStatement stmt, long id_proprietario, Telefone telefone) throws SQLException {
		stmt = con.prepareStatement(SQL_INSERIRTELEFONEPROPRIETARIO);
		stmt.setShort(1, UM);
		stmt.setInt(2, telefone.getTipo().getValor());
		stmt.setInt(3, telefone.getDdd());
		stmt.setInt(4, telefone.getNumero());
		stmt.setLong(5, id_proprietario);
		stmt.executeUpdate();
	}

	public List<PessoaFisica> buscarProprietarios() throws SerraImovelException {

		ServiceLocator serviceLocator = ServiceLocator.getInstance();

		Connection con = serviceLocator.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			stmt = con.prepareStatement(SQL_BUSCARPROPRIETARIOS);
			rs = stmt.executeQuery();

			List<PessoaFisica> lstProprietarios = new ArrayList<PessoaFisica>();

			while (rs.next()) {
				PessoaFisica func = criarProprietario(rs);
				lstProprietarios.add(func);
			}

			return lstProprietarios;

		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer(
					"N&#227;o foi poss&#237;vel buscar os Propriet&#225;rios.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		} finally {
			serviceLocator.closeConnection(con, stmt, rs);
		}
	}

	private PessoaFisica criarProprietario(ResultSet rs) throws SQLException {

		PessoaFisica prop = new PessoaFisica();

		prop.setId(rs.getLong("id_proprietario"));
		prop.setSexo(TipoDeSexo.fromInt(rs.getInt("sexo")));
		prop.setDataNascimento(rs.getDate("data_nascimento"));
		prop.setNome(rs.getString("nome"));

		return prop;
	}
	
	public PessoaFisica buscarPorProprietario(Integer usuario) throws SerraImovelException {

		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection con = serviceLocator.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			stmt = con.prepareStatement(SQL_BUSCARPORPROPRIETARIO);
			stmt.setInt(1, usuario);
			rs = stmt.executeQuery();

			PessoaFisica pessoaFisica = new PessoaFisica();

			while (rs.next()) {

				pessoaFisica.setId(rs.getLong("id_proprietario"));
				pessoaFisica.setNome(rs.getString("nome"));
				pessoaFisica.setSexo(TipoDeSexo.fromInt(rs.getInt("sexo")));
				pessoaFisica.setDataNascimento(rs.getDate("data_nascimento"));

				Email email = new Email();
				email.setTipo(TipoDeEmail.fromInt(rs.getInt("tipoEmail")));
				email.setEndereco(rs.getString("enderecoEmail"));

				pessoaFisica.setEmail(email);

				Telefone telefone = new Telefone();
				telefone.setTipo(TipoDeTelefone.fromInt(rs
						.getInt("tipoTelefone")));
				telefone.setDdd(rs.getInt("dddTelefone"));
				telefone.setNumero(rs.getInt("numeroTelefone"));

				pessoaFisica.setTelefone(telefone);

			}

			return pessoaFisica;

		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer(
					"N&#227;o foi poss&#237;vel buscar o propriet&#225;rio.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		} finally {
			serviceLocator.closeConnection(con, stmt, rs);
		}
	}

	public void atualizar(PessoaFisica pessoa) throws SerraImovelException {
		
		ServiceLocator pool = ServiceLocator.getInstance();
		Connection con = pool.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			con.setAutoCommit(false);
			
			stmt = con.prepareStatement(SQL_ATUALIZARPROPRIETARIO);
			stmt.setString(1, pessoa.getNome());
			stmt.setInt(2, pessoa.getSexo().getValor());
			java.util.Date dataNascimento = pessoa.getDataNascimento();
			stmt.setDate(3, DataUtils.utilDateToSqlDate(dataNascimento));
			stmt.setLong(4, pessoa.getId());
			stmt.executeUpdate();
			
			this.gravarEmail(con, stmt, pessoa.getId(), pessoa.getEmail());
			
			this.gravarTelefone(con, stmt, pessoa.getId(), pessoa.getTelefone());
				
			con.commit();
		
		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer(
					"N&#227;o foi poss&#237;vel realizar a grava&#231;&#227;o.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		} finally {
			pool.closeConnection(con, stmt);
		}
	}
	
	private void excluirProprietario(Connection con, PreparedStatement stmt, long id_proprietario) throws SQLException {
		stmt = con.prepareStatement(SQL_EXCLUIRPROPRIETARIO);
		stmt.setLong(1, id_proprietario);
		stmt.executeUpdate();
	}	
	
	public Integer excluirPorProprietarioGeral(Integer pessoa) throws SerraImovelException {
		
		ServiceLocator pool = ServiceLocator.getInstance();
		Connection con = pool.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			con.setAutoCommit(false);
			
			this.excluirTelefone(con, stmt, pessoa);
			this.excluirEmail(con, stmt, pessoa);
			this.excluirProprietario(con, stmt, pessoa);			
			
			con.commit();
		
		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer(
					"N&#227;o foi poss&#237;vel realizar a exclus&#231;&#227;o.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		} finally {
			pool.closeConnection(con, stmt);
		}
		return pessoa;
	}
	
}
