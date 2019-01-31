
<%
	Exception exc = (Exception) request.getAttribute("excecao");
	if (exc == null) {
		exc = new Exception("Ocorreu um erro desconhecido.");
	}
	String mensagem = exc.getMessage().replaceAll("\n", "<br />");
%>

<jsp:include page="../inc/cabecalho.jsp" />
<jsp:include page="../inc/barraLateral.jsp" />

<div id="conteudo">

	<h1>Opera&#231;&#227;o n&#227;o realizada</h1>
	<p><%=mensagem%></p>

</div>

<jsp:include page="../inc/rodape.jsp" />