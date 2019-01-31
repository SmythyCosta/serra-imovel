package com.serraimovel.command;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;

import com.serraimovel.bo.domino.CategoriaDeImovel;
import com.serraimovel.bo.domino.SituacaoDeImovel;
import com.serraimovel.bo.domino.TipoDeImovel;
import com.serraimovel.dto.Imovel;
import com.serraimovel.excecao.SerraImovelException;
import com.serraimovel.bo.ImovelBO;


public class CmdGravarImovel extends CmdAbstrato {

	@Override
	public void processar() throws ServletException, IOException {

		String url = null;
		try {

			Imovel imovel = this.criarImovel();
			ImovelBO bo = new ImovelBO();

			bo.gravarIMOVEL(imovel);

			StringBuffer msg = new StringBuffer();
			msg.append("Imovel gravado com sucesso.");

			request.setAttribute("mensagem", msg);

			url = "/jsp/sucessoOperacao.jsp";

		} catch (SerraImovelException exc) {
			request.setAttribute("excecao", exc);
			url = "/jsp/errorPage.jsp";
		}
		forward(url);

	}

	private Imovel criarImovel() throws SerraImovelException {
		Imovel imovel = new Imovel();

		String id = request.getParameter("id_imovel");
		if (id != null && !id.trim().equals("")) {
			imovel.setId(Long.parseLong(id));
		}

		imovel.setNome(request.getParameter("nome"));
		long prop = Integer.parseInt(request.getParameter("proprietario"));
		imovel.setIdProprietario(prop);
		int tipo = Integer.parseInt(request.getParameter("tipo"));
		imovel.setTipoDeImovel(TipoDeImovel.fromInt(tipo));
		int categoria = Integer.parseInt(request.getParameter("categoria"));
		imovel.setCategoriaDeImovel(CategoriaDeImovel.fromInt(categoria));
		int situacao = Integer.parseInt(request.getParameter("situacao"));
		imovel.setSituacaoDeImovel(SituacaoDeImovel.fromInt(situacao));
		BigDecimal areaT = BigDecimal.valueOf(Double.parseDouble(request.getParameter("areaTotal")));
		imovel.setAreaTotal(areaT);
		BigDecimal areaC = BigDecimal.valueOf(Double.parseDouble(request.getParameter("areaConstruida")));
		imovel.setAreaConstruida(areaC);
		BigDecimal preco = BigDecimal.valueOf(Double.parseDouble(request.getParameter("preco")));
		imovel.setPreco(preco);
		imovel.setLocal(request.getParameter("local"));
		imovel.setCidade(request.getParameter("cidade"));
		imovel.setUf(request.getParameter("uf"));
		imovel.setImg(request.getParameter("img"));
		imovel.setDescricao(request.getParameter("descricao"));

		return imovel;
	}

}
