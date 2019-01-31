<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="com.serraimovel.bo.domino.TipoDeSexo"%>
<%@ page import="com.serraimovel.bo.domino.TipoDeEmail"%>
<%@ page import="com.serraimovel.bo.domino.TipoDeTelefone"%>

<jsp:include page="../inc/cabecalho.jsp" />
<jsp:include page="../inc/barraLateral.jsp" />

<div id="conteudo">
	<form method="post" action="controller"
		onSubmit="return
validarCampos(this);" id="usuario">
		<fieldset>
			<legend>Cadastro de propriet&#225;rio</legend>
			<fieldset id="secao1">
				<legend id="secao1">Dados b&#225;sicos</legend>
				<div>
					<label for="nome">Nome</label> <label for="tipoSexo">Sexo</label> <label
						for="dataNascimento">Data de nascimento</label>
				</div>
				<div>
					<input type="hidden" value="${proprietario.id}"
						name="id_proprietario" id="id_proprietario" /> <input type="text"
						value="${proprietario.nome}" name="nome" id="nome" /> <select
						name="sexo" id="tipoSexo">
						<option value="0" selected="selected">Selecione...</option>
						<c:forEach var="tipo" items="<%=TipoDeSexo.values()%>">
							<c:if test="${tipo.valor == proprietario.sexo.valor}">
								<option value="${tipo.valor}" selected="selected">${tipo.nome}</option>
							</c:if>
							<c:if test="${tipo.valor != proprietario.sexo.valor}">
								<option value="${tipo.valor}">${tipo.nome}</option>
							</c:if>
						</c:forEach>
					</select> <input type="text" value="<fmt:formatDate dateStyle="medium" value="${proprietario.dataNascimento}"/>"
						onkeypress="formatarEntrada(this, '99/99/9999', event);"
						name="dataNascimento" id="dataNascimento" />
				</div>
			</fieldset>
			<fieldset id="secao2">
				<legend id="secao3">Contato</legend>
				<p>
					<label for="endereco">E-mail</label>
					<c:if test="${not empty proprietario.email.endereco}">
						<input type="text" value="${proprietario.email.endereco}"
							readonly="readonly" name="endereco" id="endereco" />
					</c:if>
					<c:if test="${empty proprietario.email.endereco}">
						<input type="text" value="" name="endereco" id="endereco" />
					</c:if>
					<label for="tipoEmail">Tipo</label> <select name="tipoEmail"
						id="tipoEmail">
						<option value="0" selected="selected">Selecione...</option>
						<c:forEach var="tipo" items="<%=TipoDeEmail.values()%>">
							<c:if test="${tipo.valor == proprietario.email.tipo.valor}">
								<option value="${tipo.valor}" selected="selected">${tipo.nome}</option>
							</c:if>
							<c:if test="${tipo.valor != proprietario.email.tipo.valor}">
								<option value="${tipo.valor}">${tipo.nome}</option>
							</c:if>
						</c:forEach>
					</select>
				</p>
				<p>
					<label for="telefoneGrupo">Telefone</label> <input type="text"
						value="${proprietario.telefone.ddd}" name="ddd" id="ddd" /> <input
						type="text" value="${proprietario.telefone.numero}" name="numero"
						id="numero" /> <label for="tipoTelefone">Tipo</label> <select
						name="tipoTelefone" id="tipoTelefone">
						<option value="0" selected="selected">Selecione...</option>
						<c:forEach var="tipo" items="<%=TipoDeTelefone.values()%>">
							<c:if test="${tipo.valor == proprietario.telefone.tipo.valor}">
								<option value="${tipo.valor}" selected="selected">${tipo.nome}</option>
							</c:if>
							<c:if test="${tipo.valor != proprietario.telefone.tipo.valor}">
								<option value="${tipo.valor}">${tipo.nome}</option>
							</c:if>
						</c:forEach>
					</select>
				</p>
			</fieldset>			
		</fieldset>
		<input type="hidden" name="cmd" value="GravarProprietario">
		<div id="botao">
			<button type="reset" id="limpar">
				<img alt="Limpar" src="../img/limpar.gif"> Limpar
			</button>
			<button type="submit" id="gravar">
				<img alt="Gravar" src="../img/submeter.gif"> Gravar
			</button>
		</div>
	</form>
</div>
<jsp:include page="../inc/rodape.jsp" />