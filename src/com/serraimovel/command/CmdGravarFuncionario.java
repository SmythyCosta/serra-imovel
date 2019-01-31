package com.serraimovel.command;

import java.io.IOException;

import javax.servlet.ServletException;

import com.serraimovel.bo.FuncionarioBO;
import com.serraimovel.bo.dominio.TipoDeEmail;
import com.serraimovel.bo.dominio.TipoDeSexo;
import com.serraimovel.bo.dominio.TipoDeTelefone;
import com.serraimovel.dto.Credencial;
import com.serraimovel.dto.Email;
import com.serraimovel.dto.PessoaFisica;
import com.serraimovel.dto.Telefone;
import com.serraimovel.excecao.SerraImovelException;
import com.serraimovel.util.DataUtils;
import com.serraimovel.util.HashUtils;

public class CmdGravarFuncionario extends CmdAbstrato {

	@Override
	public void processar() throws ServletException, IOException {

		String url = null;
		try {

			PessoaFisica pessoa = this.criarFuncionario();
			FuncionarioBO bo = new FuncionarioBO();

			bo.gravarFUNC(pessoa);

			StringBuffer msg = new StringBuffer();
			msg.append("Funcion&#225;rio gravado com sucesso.");

			request.setAttribute("mensagem", msg);

			url = "/jsp/sucessoOperacao.jsp";

		} catch (SerraImovelException exc) {
			request.setAttribute("excecao", exc);
			url = "/jsp/errorPage.jsp";
		}
		forward(url);

	}

	private PessoaFisica criarFuncionario() throws SerraImovelException {
		PessoaFisica pessoa = new PessoaFisica();

		String id = request.getParameter("id_funcionario");
		if (id != null && !id.trim().equals("")) {
			pessoa.setId(Long.parseLong(id));
		}

		pessoa.setNome(request.getParameter("nome"));
		int sexo = Integer.parseInt(request.getParameter("sexo"));
		pessoa.setSexo(TipoDeSexo.fromInt(sexo));
		String strData = request.getParameter("dataNascimento");
		java.util.Date data = DataUtils.strToUtilDate(strData, "dd/MM/yyyy");
		pessoa.setDataNascimento(data);

		criarEmailDe(pessoa);

		criarTelefoneDe(pessoa);

		criarCredencialDe(pessoa);

		return pessoa;
	}

	private void criarEmailDe(PessoaFisica pessoa) {

		Email email = new Email();
		int tipo = Integer.parseInt(request.getParameter("tipoEmail"));
		email.setTipo(TipoDeEmail.fromInt(tipo));
		email.setEndereco(request.getParameter("endereco"));
		pessoa.setEmail(email);

	}

	private void criarTelefoneDe(PessoaFisica pessoa) {

		Telefone telefone = new Telefone();
		int tipo = Integer.parseInt(request.getParameter("tipoTelefone"));
		telefone.setTipo(TipoDeTelefone.fromInt(tipo));
		telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
		telefone.setNumero(Integer.parseInt(request.getParameter("numero")));
		pessoa.setTelefone(telefone);

	}

	private void criarCredencialDe(PessoaFisica pessoa)
			throws SerraImovelException {
		
		Credencial credencial = new Credencial();
		credencial.setUsuario(request.getParameter("endereco"));
		String senha = HashUtils.criptografarMD5(request.getParameter("senha"));
		
		credencial.setSenha(senha);
		
		pessoa.setCredencial(credencial);
	}

}
