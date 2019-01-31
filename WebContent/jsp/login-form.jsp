<jsp:include page="../inc/cabecalho.jsp" />
<jsp:include page="../inc/barraLateral.jsp" />
<%
	request.getSession().setAttribute("url", request.getRequestURL());
%>
<div id="conteudo">
	<form action="j_security_check" method="post" id="login">
		<fieldset>
			<legend>Credenciais</legend>
			<label for="usuario">Usu&#225;rio (Email)</label> 
			<input	type="text" name="j_username" id="usuario" /> 
			<label for="senha">Senha</label>
			<input type="password" name="j_password" id="senha" />
		</fieldset>
		<button type="reset" id="limpar">
			<img alt="Limpar" src="../img/limpar.gif"> Limpar
		</button>
		<button type="submit" id="entrar">
			<img alt="Entrar" src="../img/submeter.gif"> Entrar
		</button>
	</form>
</div>
<jsp:include page="../inc/rodape.jsp" />