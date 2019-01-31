<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="com.serraimovel.bo.dominio.TipoDeSexo"%>
<%@ page import="com.serraimovel.bo.dominio.TipoDeEmail"%>
<%@ page import="com.serraimovel.bo.dominio.TipoDeTelefone"%>

<jsp:include page="../inc/cabecalho.jsp" />
<jsp:include page="../inc/barraLateral.jsp" />

<div id="conteudo">
	<form method="post" action="controller"
		onSubmit="return
validarCampos(this);" id="usuario">
		<fieldset>
			<legend>Cadastro de funcion&#225;rio</legend>
			<fieldset id="secao1">
				

				<legend id="secao1">Dados b&#225;sicos</legend>
				<div>
					<label for="nome">Nome</label> 
					<label for="tipoSexo">Sexo</label> 
					<label for="dataNascimento">Data de nascimento</label>
				</div>
				<div>
					<input type="hidden" value="${funcionario.id}"
						name="id_funcionario" id="id_funcionario" /> 

					<input type="text" value="${funcionario.nome}" name="nome" id="nome" /> 
					<select name="sexo" id="tipoSexo">
						<option value="0" selected="selected">Selecione...</option>
						<c:forEach var="tipo" items="<%=TipoDeSexo.values()%>">
							<c:if test="${tipo.valor == funcionario.sexo.valor}">
								<option value="${tipo.valor}" selected="selected">${tipo.nome}</option>
							</c:if>
							<c:if test="${tipo.valor != funcionario.sexo.valor}">
								<option value="${tipo.valor}">${tipo.nome}</option>
							</c:if>
						</c:forEach>
					</select> <input type="text" value="<fmt:formatDate dateStyle="medium" value="${funcionario.dataNascimento}"/>"
						onkeypress="formatarEntrada(this, '99/99/9999', event);"
						name="dataNascimento" id="dataNascimento" />
				</div>
			</fieldset>
			<fieldset id="secao2">
				<legend id="secao3">Contato</legend>
				<p>
					<label for="endereco">E-mail</label>
					<c:if test="${not empty funcionario.email.endereco}">
						<input type="text" value="${funcionario.email.endereco}"
							readonly="readonly" name="endereco" id="endereco" />
					</c:if>
					<c:if test="${empty funcionario.email.endereco}">
						<input type="text" value="" name="endereco" id="endereco" />
					</c:if>
					<label for="tipoEmail">Tipo</label> <select name="tipoEmail"
						id="tipoEmail">
						<option value="0" selected="selected">Selecione...</option>
						<c:forEach var="tipo" items="<%=TipoDeEmail.values()%>">
							<c:if test="${tipo.valor == funcionario.email.tipo.valor}">
								<option value="${tipo.valor}" selected="selected">${tipo.nome}</option>
							</c:if>
							<c:if test="${tipo.valor != funcionario.email.tipo.valor}">
								<option value="${tipo.valor}">${tipo.nome}</option>
							</c:if>
						</c:forEach>
					</select>
				</p>
				<p>
					<label for="telefoneGrupo">Telefone</label> <input type="text"
						value="${funcionario.telefone.ddd}" name="ddd" id="ddd" /> <input
						type="text" value="${funcionario.telefone.numero}" name="numero"
						id="numero" /> <label for="tipoTelefone">Tipo</label> <select
						name="tipoTelefone" id="tipoTelefone">
						<option value="0" selected="selected">Selecione...</option>
						<c:forEach var="tipo" items="<%=TipoDeTelefone.values()%>">
							<c:if test="${tipo.valor == funcionario.telefone.tipo.valor}">
								<option value="${tipo.valor}" selected="selected">${tipo.nome}</option>
							</c:if>
							<c:if test="${tipo.valor != funcionario.telefone.tipo.valor}">
								<option value="${tipo.valor}">${tipo.nome}</option>
							</c:if>
						</c:forEach>
					</select>
				</p>
			</fieldset>
			<fieldset id="secao3">
				<legend id="secao2">Senha</legend>
				<div>
					<label for="senha">Senha</label> <label for="senhaConfirmacao">Confirmar
						senha</label>
				</div>
				<div>
					<input type="password" name="senha" id="senha" /> <input
						type="password" name="senhaConfirmacao" id="senhaConfirmacao" />
				</div>
			</fieldset>
		</fieldset>
		<input type="hidden" name="cmd" value="GravarFuncionario">
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