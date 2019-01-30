function formatarEntrada(objeto, sMask, evtKeyPress) {
	var i, nCount, sValue, fldLen, mskLen, bolMask, sCod, nTecla;

	if (document.all) {
		nTecla = evtKeyPress.keyCode;
	} else if (document.layers) {
		nTecla = evtKeyPress.which;
	} else {
		nTecla = evtKeyPress.which;
		if (nTecla == 8) {
			return true;
		}
	}

	sValue = objeto.value;

	sValue = sValue.toString().replace("-", "");
	sValue = sValue.toString().replace("-", "");
	sValue = sValue.toString().replace(".", "");
	sValue = sValue.toString().replace(".", "");
	sValue = sValue.toString().replace("/", "");
	sValue = sValue.toString().replace("/", "");
	sValue = sValue.toString().replace(":", "");
	sValue = sValue.toString().replace(":", "");
	sValue = sValue.toString().replace("(", "");
	sValue = sValue.toString().replace("(", "");
	sValue = sValue.toString().replace(")", "");
	sValue = sValue.toString().replace(")", "");
	sValue = sValue.toString().replace(" ", "");
	sValue = sValue.toString().replace(" ", "");
	fldLen = sValue.length;
	mskLen = sMask.length;

	i = 0;
	nCount = 0;
	sCod = "";
	mskLen = fldLen;

	while (i <= mskLen) {
		bolMask = ((sMask.charAt(i) == "-") || (sMask.charAt(i) == ".")
				|| (sMask.charAt(i) == "/") || (sMask.charAt(i) == ":"));
		bolMask = bolMask
				|| ((sMask.charAt(i) == "(") || (sMask.charAt(i) == ")") || (sMask
						.charAt(i) == " "));

		if (bolMask) {
			sCod += sMask.charAt(i);
			mskLen++;
		} else {
			sCod += sValue.charAt(nCount);
			nCount++;
		}

		i++;
	}

	objeto.value = sCod;

	if (nTecla != 8) { // backspace
		if (sMask.charAt(i - 1) == "9") { // apenas n�meros...
			return ((nTecla > 47) && (nTecla < 58));
		} else { // qualquer caracter...
			return true;
		}
	} else {
		return true;
	}
}

function verificarFornecimentoDeCamposObrigatorios(form) {

	var mensagem = "Corrija os seguintes problemas: \n";

	if (form.nome.value == "") {
		mensagem = mensagem + "\n* Nome n\u00E3o informado";
	}

	if (form.tipoSexo.value == "0") {
		mensagem = mensagem + "\n* Sexo  n\u00E3o informado";
	}

	if (form.dataNascimento.value == "") {
		mensagem = mensagem + "\n* Data de nascimento  n\u00E3o informada";
	}

	if (form.endereco.value == "") {
		mensagem = mensagem + "\n* Endere\u00e7o do e-mail  n\u00E3o informado";
	}

	if (form.tipoEmail.value == "0") {
		mensagem = mensagem + "\n* Tipo do e-mail  n\u00E3o informado";
	}

	if (form.ddd.value == "") {
		mensagem = mensagem + "\n* DDD do telefone  n\u00E3o informado";
	}

	if (form.numero.value == "") {
		mensagem = mensagem + "\n* N\u00FAmero do telefone  n\u00E3o informado";
	}

	if (form.tipoTelefone.value == "0") {
		mensagem = mensagem + "\n* Tipo do telefone  n\u00E3o informado";
	}

	var senha = trim(form.senha.value);

	if (senha == "") {
		mensagem = mensagem + "\n* Senha n\u00E3o informada ou inv\u00E1lida";
	}

	var senhaConfirmacao = trim(form.senhaConfirmacao.value);

	if (senhaConfirmacao == "") {
		mensagem = mensagem
				+ "\n* Confirma\u00e7\u00E3o de senha n\u00E3o informada ou inv\u00E1lida";
	}

	if (mensagem.length > "33") {
		alert(mensagem);

		form.nome.focus();

		return false;
	}

	return true;
}

function validarData(form) {
	var data = form.dataNascimento.value;
	var expressaoRegular = /^((0[1-9]|[12]\d)\/(0[1-9]|1[0-2])|30\/(0[13-9]|1[0-2])|31\/(0[13578]|1[02]))\/\d{4}$/;
	var b = expressaoRegular.test(data);
	
	if (b == false) {
		var mensagem = "Corrija o seguinte problema: \n";
		mensagem = mensagem + "\n* Data inv\u00E1lida.";		
		
		alert(mensagem);
		
		form.dataNascimento.select();
	}
	
	return b;
}

function validarEmail(form) {
	var expressaoRegular = /^[\w-]+(\.[\w-]+)*@(([A-Za-z\d][A-Za-z\d-]{0,61}[A-Za-z\d]\.)+[A-Za-z]{2,6}|\[\d{1,3}(\.\d{1,3}){3}\])$/;	
	var b = expressaoRegular.test(form.endereco.value);
	if (b == false) {
		var mensagem = "Corrija o seguinte problema: \n";
		mensagem = mensagem + "\n* E-mail inv\u00E1lido.";		
		
		alert(mensagem);
		
		form.endereco.select();
	}
	
	return b;
}

function validarSenha(form) {
	var senha = form.senha.value;
	var senhaConfirmacao = form.senhaConfirmacao.value;

	if (senha != senhaConfirmacao) {
		var mensagem = "Corrija o seguinte problema: \n";
		mensagem = mensagem + "\n* Senhas n\u00E3o conferem";
		
		alert(mensagem);

		form.senha.select();
		
		return false;
	}

	return true;
}

function validarCampos(form) {
	var b = verificarFornecimentoDeCamposObrigatorios(form);

	if (b == false) {
		return b;
	}

	b = validarData(form);

	if (b == false) {
		return b;
	}

	b = validarEmail(form);

	if (b == false) {
		return b;
	}	
	
	b = validarSenha(form);

	if (b == false) {
		return b;
	}

	return b;
}

function trim(str) {
	return str.replace(/^\s+|\s+$/g, "");
}