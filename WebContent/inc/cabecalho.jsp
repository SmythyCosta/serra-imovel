<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="../js/serraimovel.js" type=text/javascript></script>
<link href="../css/serraimovel.css" rel="Stylesheet" type="text/css">
<link href="../css/displaytag.css" rel="Stylesheet" type="text/css">

<title>| Serra Imóvel |</title>
</head>
<body>

	<div id="detalhe">
		
		<%
			if (request.getUserPrincipal() == null) {
		%>
			<p><a href="autenticar.jsp">Entrar</a></p>
		<%
			} else {
		%>
		
		<p>   
			
			Usu&#225;rio: <%out.println(request.getUserPrincipal().getName());%> 
				
			&nbsp;&nbsp; | &nbsp;&nbsp;
			
			<a href="controller?cmd=Sair"> <img src="../img/sair.png" /> Sair
			</a>
		</p>
		<%
			}
		%>
	</div>

	<div id="cabecalho">
		<h1>Serra Imóvel</h1>
	</div>