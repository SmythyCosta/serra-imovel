package com.serraimovel.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class Valor implements DisplaytagColumnDecorator {

	private String format = "R$ #,###,##0.00";

	@Override
	public Object decorate(Object columnValue, PageContext pageContext,
			MediaTypeEnum media) throws DecoratorException {
		
		BigDecimal valor = (BigDecimal) columnValue;

		String strValor = valorToString(valor);

		return strValor;

	}

	private String valorToString(BigDecimal valor) {
		DecimalFormat decimalFormat = new DecimalFormat(format);
		
		String strValor = decimalFormat.format(valor.doubleValue());
		
		return strValor;
	}

}
