package com.serraimovel.bo;

import java.util.List;

import com.serraimovel.dao.ImovelDAO;
import com.serraimovel.dto.Imovel;
import com.serraimovel.excecao.SerraImovelException;

public class ImovelBO {

	public void gravarIMOVEL(Imovel imovel) throws SerraImovelException {

		ImovelDAO dao = new ImovelDAO();

		if (imovel.getId() == 0) {
			dao.inserir(imovel);
		} else {
			dao.atualizar(imovel);
		}

	}

	public Imovel buscarPorImovel(Integer imo)
			throws SerraImovelException {
		
		ImovelDAO dao = new ImovelDAO();		
		
		Imovel imovel = dao.buscarPorImovel(imo);
		
		return imovel;
	}
	
	public Integer excluirPorImovel(Integer imo)
			throws SerraImovelException {
		
		ImovelDAO dao = new ImovelDAO();		
		
		Integer imovel = dao.excluirPorImovelGeral(imo);
		
		return imovel;
	}

	public List<Imovel> buscarImovels() throws SerraImovelException {

		ImovelDAO dao = new ImovelDAO();

		List<Imovel> lstImoveis = dao.buscarImovels();

		return lstImoveis;
	}

}
