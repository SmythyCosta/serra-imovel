package com.serraimovel.dao;

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
	
	
}
