package com.serraimovel.dto;

import java.math.BigDecimal;

import com.serraimovel.bo.domino.CategoriaDeImovel;
import com.serraimovel.bo.domino.SituacaoDeImovel;
import com.serraimovel.bo.domino.TipoDeImovel;

public class Imovel extends ObjetoDoDominio {
	
	private TipoDeImovel tipoDeImovel;
	private CategoriaDeImovel categoriaDeImovel;
	private SituacaoDeImovel situacaoDeImovel;
	private String nome;
	private String descricao;
	private String local;
	private String cidade;
	private String uf;
	private BigDecimal areaTotal;
	private BigDecimal areaConstruida;
	private BigDecimal preco;
	private String img;
	private Long idProprietario;	
	
	public Imovel() {
		super();
	}

	public Imovel(long id) {
		super(id);
	}
	
	public TipoDeImovel getTipoDeImovel() {
		return tipoDeImovel;
	}

	public void setTipoDeImovel(TipoDeImovel tipoDeImovel) {
		this.tipoDeImovel = tipoDeImovel;
	}

	public CategoriaDeImovel getCategoriaDeImovel() {
		return categoriaDeImovel;
	}

	public void setCategoriaDeImovel(CategoriaDeImovel categoriaDeImovel) {
		this.categoriaDeImovel = categoriaDeImovel;
	}

	public SituacaoDeImovel getSituacaoDeImovel() {
		return situacaoDeImovel;
	}

	public void setSituacaoDeImovel(SituacaoDeImovel situacaoDeImovel) {
		this.situacaoDeImovel = situacaoDeImovel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public BigDecimal getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(BigDecimal areaTotal) {
		this.areaTotal = areaTotal;
	}

	public BigDecimal getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(BigDecimal areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Long getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(Long idProprietario) {
		this.idProprietario = idProprietario;
	}
	
}
