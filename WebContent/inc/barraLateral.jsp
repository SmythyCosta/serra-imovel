<div id="barraLateral">


	<%
		if (request.getUserPrincipal() == null) {
	%>
	<p>
		<a href="home.jsp">Home</a>
	</p>
	<p>
		<%
			} else {
		%>
	
	<p>
		<a href="home.jsp">Home</a>
	</p>
	<hr />
	<p>
	<p>
		<a href="controller?cmd=CadastrarFuncionario">Cadastrar
			Funcion&#225;rio</a>
	</p>
	<p>
		<a href="controller?cmd=ListarFuncionarios">Listar
			Funcion&#225;rios</a>
	</p>
	<hr />
	<p>
		<a href="controller?cmd=CadastrarProprietario">Cadastrar
			Propriet&#225;rio</a>
	</p>
	<p>
		<a href="controller?cmd=ListarProprietarios">Listar
			Propriet&#225;rios</a>
	</p>
	<hr />
	<p>
		<a href="controller?cmd=CadastrarImovel">Cadastrar
			Im&oacute;vel</a>
	</p>
	<p>
		<a href="controller?cmd=ListarImoveis">Listar
			Im&oacute;veis</a>
	</p>

	<%
		}
	%>
</div>