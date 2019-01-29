package com.serraimovel.util;

import com.serraimovel.excecao.SerraImovelException;

public class DataUtils {

	/*
	 * 
	 * This method receives a date (object of type java.util.Date),
	 * format it and return it as String.
	 * 
	 * */
	public static String utilDateToStr(java.util.Date data) {
		java.text.DateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		String strData = formatter.format(data);
		return strData;
	}

	/*
	 * 
	 * This method receives a date (object of type String),
	 * converts it to Java.util.Date and returns it.
	 * 
	 * */
	public static java.util.Date strToUtilDate(String str, String formato)throws SerraImovelException {
		
		java.text.DateFormat formatter = new java.text.SimpleDateFormat(formato);
		formatter.setLenient(false);
		java.util.Date dataConvertida = null;
		
		try {
			dataConvertida = (java.util.Date) formatter.parse(str);
			return dataConvertida;
		} catch (java.text.ParseException exc) {
			StringBuffer mensagem = new StringBuffer(
					"N&#227;o foi poss&#237;vel converter a data ou hora.");
			mensagem.append("\n\nMotivo: " + exc.getMessage());
			throw new SerraImovelException(mensagem.toString(), exc);
		}
	}
	
	/*
	 * 
	 * This method receives an object of type Java.util.Date,
	 * converts it to java.sql.Date and returns it.
	 * 
	 * */
	public static java.sql.Date utilDateToSqlDate(java.util.Date data) {
		java.sql.Date dataConvertida = new java.sql.Date(data.getTime());
		return dataConvertida;
	}
	
	/*
	 * 
	 * This method receives an object of type java.util.Date,
	 * converts it to java.sql.Time and returns it.
	 * 
	 * */
	public static java.sql.Time utilDateToSqlTime(java.util.Date hora) {
		java.sql.Time horaConvertida = new java.sql.Time(hora.getTime());
		return horaConvertida;
	}

}
