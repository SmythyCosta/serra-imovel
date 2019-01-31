<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="com.serraimovel.dto.Pessoa"%>
<jsp:include page="../inc/cabecalho.jsp" />
<jsp:include page="../inc/barraLateral.jsp" />

<div id="conteudo">
	<display:table id="imovel" name="sessionScope.imoveis" pagesize="5" sort="page" class="catalogo">
		
		<display:caption>Lista de Imoveis</display:caption>
		
		<display:column property="nome" title="Nome" />
		<display:column property="tipoDeImovel.nome" title="Tipo" />
		<display:column property="categoriaDeImovel.nome" title="Categoria" />
		<display:column property="situacaoDeImovel.nome" title="Situacao" />
		<display:column property="preco" title="Pre&#231;o" 
		decorator="com.serraimovel.util.Valor" />
				
		<display:column title="Editar" class="acao">
			<a href="controller?cmd=EditarImovel&id_imovel=${imovel.id}"> 
			<img alt="Ver detalhes" src="../img/edit.png">
			</a>
		</display:column>
		<display:column title="Excluir" class="acao">
			<a href="controller?cmd=ExcluirImovel&id_imovel=${imovel.id}"> 
			<img alt="Ver detalhes" src="../img/delete.png">
			</a>
		</display:column>
	</display:table>
</div>
<jsp:include page="../inc/rodape.jsp" />