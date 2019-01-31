<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="com.serraimovel.dto.Pessoa"%>
<jsp:include page="../inc/cabecalho.jsp" />
<jsp:include page="../inc/barraLateral.jsp" />

<div id="conteudo">
	<display:table id="proprietario" name="sessionScope.proprietarios" pagesize="5" sort="page" class="catalogo">
		
		<display:caption>Lista de Propriet&#225;rios</display:caption>
		
		<display:column property="nome" title="Nome" />
		<display:column property="dataNascimento" title="Data Nascimento"
			decorator="com.smythy.serraimovel.util.DataWrapper" />
		
		<display:column property="sexo.nome" title="Sexo" />
		
		<display:column title="Editar" class="acao">
			<a href="controller?cmd=EditarProprietario&id_proprietario=${proprietario.id}"> 
			<img alt="Ver detalhes" src="../img/edit.png">
			</a>
		</display:column>
		<display:column title="Excluir" class="acao">
			<a href="controller?cmd=ExcluirProprietario&id_proprietario=${proprietario.id}"> 
			<img alt="Ver detalhes" src="../img/delete.png">
			</a>
		</display:column>
	</display:table>
</div>
<jsp:include page="../inc/rodape.jsp" />