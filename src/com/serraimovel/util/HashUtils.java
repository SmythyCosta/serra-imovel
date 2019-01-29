package com.serraimovel.util;

import java.security.MessageDigest;

import com.serraimovel.excecao.SerraImovelException;

public class HashUtils {

	/*
	 * 
	 * This method encrypts the password
	 * (object of type String) using the MD5 algorithm..
	 * 
	 * */
	public static String criptografarMD5(String senha) throws SerraImovelException {
		
		try {
			
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			
			return hexString.toString();
		
		} catch (Exception exc) {
			StringBuffer mensagem = new StringBuffer(
					"N&#227;o foi poss&#237;vel realizar a opera&#231;&#227;o.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			mensagem.append("\n\nOcorr&#234;ncia registrada em log para	an&#225;lise.");
			throw new SerraImovelException(mensagem.toString(), exc);
		}
		
	}

}
