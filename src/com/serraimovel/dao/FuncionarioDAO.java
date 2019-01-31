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
import com.serraimovel.dto.Credencial;
import com.serraimovel.dto.Email;
import com.serraimovel.dto.PessoaFisica;
import com.serraimovel.dto.Telefone;
import com.serraimovel.excecao.SerraImovelException;
import com.serraimovel.util.DataUtils;

public class FuncionarioDAO {

	private final String SQL_INSERIRFUNC = "INSERT INTO funcionario (id_funcionario, nome, sexo, data_nascimento) VALUES (?, ?, ?, ?)";
	private final String SQL_EXCLUIRFUNC = "DELETE FROM funcionario WHERE id_funcionario = ?";
	private final String SQL_INSERIREMAILFUNC = "INSERT INTO email_funcionario (seql_email, tipo, endereco,	id_funcionario) VALUES (?, ?, ?, ?)";
	private final String SQL_EXCLUIREMAILFUNC = "DELETE FROM email_funcionario WHERE id_funcionario = ?";
	private final String SQL_INSERIRTELEFONEFUNC = "INSERT INTO telefone_funcionario (seql_telefone, tipo, ddd, numero, id_funcionario) VALUES (?, ?, ?, ?, ?)";
	private final String SQL_EXCLUIRTELEFONEFUNC = "DELETE FROM telefone_funcionario WHERE id_funcionario = ?";
	private final short UM = 1;
	
	private final String SQL_INSERIRUSUARIOSENHA = "INSERT INTO usuario_senha (usuario, senha,	id_funcionario) VALUES (?, ?, ?)";
	private final String SQL_EXCLUIRUSUARIOSENHA = "DELETE FROM usuario_senha WHERE id_funcionario = ?";
	private final String SQL_INSERIRUSUARIOPAPEL = "INSERT INTO usuario_papel (usuario, papel,	id_funcionario) VALUES (?, ?, ?)";
	private final String SQL_EXCLUIRUSUARIOPAPEL = "DELETE FROM usuario_papel WHERE id_funcionario = ?";
	private final String FUNCIONARIO = "funcionario";
	
	private final String SQL_BUSCARFUNCIONARIOS = 
			"SELECT "
			+ "f.id_funcionario, " 
			+ "f.nome, " 
			+ "f.sexo, "
			+ "f.data_nascimento, " 
			+ "ef.tipo, " 
			+ "ef.endereco, "
			+ "tf.tipo, " 
			+ "tf.ddd, " 
			+ "tf.numero " 
			+ "FROM "
			+ "funcionario f, " 
			+ "email_funcionario ef, "
			+ "telefone_funcionario tf " 
			+ "WHERE "
			+ "ef.id_funcionario = f.id_funcionario "
			+ "AND tf.id_funcionario = f.id_funcionario ";
	
	private final String SQL_ATUALIZARFUNC = 
			"UPDATE funcionario SET "
			+ "nome = ?, " 
			+ "sexo = ?, " 
			+ "data_nascimento = ? "
			+ "WHERE id_funcionario = ?";

	private final String SQL_BUSCARPORFUNCIONARIO = 
			"SELECT "
			+ "f.id_funcionario, "
			+ "f.nome, "
			+ "f.sexo, "
			+ "f.data_nascimento, "
			+ "e.tipo tipoEmail, "
			+ "e.endereco enderecoEmail, "
			+ "t.tipo	tipoTelefone, "
			+ "t.ddd dddTelefone, "
			+ "t.numero numeroTelefone, "
			+ "up.usuario, "
			+ "up.papel, "
			+ "us.usuario, "
			+ "us.senha "
			+ "FROM	"
			+ "funcionario f, " 
			+"usuario_senha us, " 
			+"usuario_papel up, " 
			+"email_funcionario e, " 
			+"telefone_funcionario t "
			+ "WHERE	f.id_funcionario = ? "
			+ "AND f.id_funcionario = us.id_funcionario "
			+ "AND f.id_funcionario = up.id_funcionario "
			+ "AND f.id_funcionario = e.id_funcionario "
			+ "AND f.id_funcionario =	t.id_funcionario";
	
	public void inserir(PessoaFisica pessoa) throws SerraImovelException {

		ServiceLocator pool = ServiceLocator.getInstance();
		Connection con = pool.getConnection();
		PreparedStatement stmt = null;
		
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(SQL_INSERIRFUNC);

			GeradorDeChave geradorDeChave = new GeradorDeChave("funcionario");

			long codigo = geradorDeChave.getProximoCodigo();

			stmt.setLong(1, codigo);
			stmt.setString(2, pessoa.getNome());
			stmt.setInt(3, pessoa.getSexo().getValor());
			java.util.Date dataNascimento = pessoa.getDataNascimento();
			stmt.setDate(4, DataUtils.utilDateToSqlDate(dataNascimento));

			stmt.executeUpdate();

			this.gravarEmail(con, stmt, codigo, pessoa.getEmail());

			this.gravarTelefone(con, stmt, codigo, pessoa.getTelefone());

			this.gravarCredencial(con, stmt, codigo, pessoa.getCredencial());

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
	
	private void gravarEmail(Connection con, PreparedStatement stmt,
			long id_funcionario, Email email) throws SQLException {

		this.excluirEmail(con, stmt, id_funcionario);
		this.inserirEmail(con, stmt, id_funcionario, email);
	}

	private void excluirEmail(Connection con, PreparedStatement stmt,
			long id_funcionario) throws SQLException {

		stmt = con.prepareStatement(SQL_EXCLUIREMAILFUNC);
		stmt.setLong(1, id_funcionario);
		stmt.executeUpdate();
	}
	
	private void inserirEmail(Connection con, PreparedStatement stmt,
			long id_funcionario, Email email) throws SQLException {

		stmt = con.prepareStatement(SQL_INSERIREMAILFUNC);
		stmt.setShort(1, UM);
		stmt.setInt(2, email.getTipo().getValor());
		stmt.setString(3, email.getEndereco());
		stmt.setLong(4, id_funcionario);
		stmt.executeUpdate();

	}
	
	private void gravarTelefone(Connection con, PreparedStatement stmt,
			long id_funcionario, Telefone telefone) throws SQLException {

		this.excluirTelefone(con, stmt, id_funcionario);
		this.inserirTelefone(con, stmt, id_funcionario, telefone);
	}
	
	private void excluirTelefone(Connection con, PreparedStatement stmt,
			long id_funcionario) throws SQLException {

		stmt = con.prepareStatement(SQL_EXCLUIRTELEFONEFUNC);
		stmt.setLong(1, id_funcionario);
		stmt.executeUpdate();
	}	
	
	private void inserirTelefone(Connection con, PreparedStatement stmt,
			long id_funcionario, Telefone telefone) throws SQLException {

		stmt = con.prepareStatement(SQL_INSERIRTELEFONEFUNC);
		stmt.setShort(1, UM);
		stmt.setInt(2, telefone.getTipo().getValor());
		stmt.setInt(3, telefone.getDdd());
		stmt.setInt(4, telefone.getNumero());
		stmt.setLong(5, id_funcionario);
		stmt.executeUpdate();
	}
	
	private void gravarCredencial(Connection con, PreparedStatement stmt,
			long id, Credencial credencial) throws SerraImovelException,
			SQLException {
		stmt = con.prepareStatement(SQL_EXCLUIRUSUARIOSENHA);
		stmt.setLong(1, id);
		stmt.executeUpdate();
		stmt = con.prepareStatement(SQL_INSERIRUSUARIOSENHA);
		stmt.setString(1, credencial.getUsuario());
		stmt.setString(2, credencial.getSenha());
		stmt.setLong(3, id);
		stmt.executeUpdate();
		stmt = con.prepareStatement(SQL_EXCLUIRUSUARIOPAPEL);
		stmt.setLong(1, id);
		stmt.executeUpdate();
		stmt = con.prepareStatement(SQL_INSERIRUSUARIOPAPEL);
		stmt.setString(1, credencial.getUsuario());
		stmt.setString(2, FUNCIONARIO);
		stmt.setLong(3, id);
		stmt.executeUpdate();
	}
	
	private void excluirCredencial(Connection con, PreparedStatement stmt,
			long id_funcionario) throws SerraImovelException,
			SQLException {
		stmt = con.prepareStatement(SQL_EXCLUIRUSUARIOSENHA);
		stmt.setLong(1, id_funcionario);
		stmt.executeUpdate();		
		stmt = con.prepareStatement(SQL_EXCLUIRUSUARIOPAPEL);
		stmt.setLong(1, id_funcionario);
		stmt.executeUpdate();		
	}
	
	public List<PessoaFisica> buscarFuncionarios() throws SerraImovelException {

		ServiceLocator serviceLocator = ServiceLocator.getInstance();

		Connection con = serviceLocator.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			stmt = con.prepareStatement(SQL_BUSCARFUNCIONARIOS);
			rs = stmt.executeQuery();

			List<PessoaFisica> lstFuncionarios = new ArrayList<PessoaFisica>();

			while (rs.next()) {
				PessoaFisica func = criarFuncionario(rs);
				lstFuncionarios.add(func);
			}

			return lstFuncionarios;

		} catch (SQLException exc) {
			StringBuffer mensagem = new StringBuffer(
					"N&#227;o foi poss&#237;vel buscar os Funcion&#225;rios.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		} finally {
			serviceLocator.closeConnection(con, stmt, rs);
		}
	}
	
	private PessoaFisica criarFuncionario(ResultSet rs) throws SQLException {

		PessoaFisica func = new PessoaFisica();

		func.setId(rs.getLong("id_funcionario"));
		func.setSexo(TipoDeSexo.fromInt(rs.getInt("sexo")));
		func.setDataNascimento(rs.getDate("data_nascimento"));
		func.setNome(rs.getString("nome"));

		return func;
	}
	
	public PessoaFisica buscarPorFuncionario(Integer usuario)
			throws SerraImovelException {

		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection con = serviceLocator.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			stmt = con.prepareStatement(SQL_BUSCARPORFUNCIONARIO);
			stmt.setInt(1, usuario);
			rs = stmt.executeQuery();

			PessoaFisica pessoaFisica = new PessoaFisica();

			while (rs.next()) {

				pessoaFisica.setId(rs.getLong("id_funcionario"));
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
					"N&#227;o foi poss&#237;vel buscar o funcion&#225;rio.");
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
			
			stmt = con.prepareStatement(SQL_ATUALIZARFUNC);
			stmt.setString(1, pessoa.getNome());
			stmt.setInt(2, pessoa.getSexo().getValor());
			java.util.Date dataNascimento = pessoa.getDataNascimento();
			stmt.setDate(3, DataUtils.utilDateToSqlDate(dataNascimento));
			stmt.setLong(4, pessoa.getId());
			stmt.executeUpdate();
			
			this.gravarEmail(con, stmt, pessoa.getId(), pessoa.getEmail());
			
			this.gravarTelefone(con, stmt, pessoa.getId(), pessoa.getTelefone());
			
			this.gravarCredencial(con, stmt, pessoa.getId(),
					pessoa.getCredencial());
			
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
	
	private void excluirFuncionario(Connection con, PreparedStatement stmt,
			long id_funcionario) throws SQLException {

		stmt = con.prepareStatement(SQL_EXCLUIRFUNC);
		stmt.setLong(1, id_funcionario);
		stmt.executeUpdate();
	}
	
	
	
	public Integer excluirPorFuncionarioGeral(Integer pessoa) throws SerraImovelException {
		ServiceLocator pool = ServiceLocator.getInstance();
		Connection con = pool.getConnection();
		PreparedStatement stmt = null;
		try {
			
			con.setAutoCommit(false);
			
			this.excluirTelefone(con, stmt, pessoa);
			this.excluirEmail(con, stmt, pessoa);
			this.excluirCredencial(con, stmt, pessoa);
			this.excluirFuncionario(con, stmt, pessoa);
			
			
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
