package com.serraimovel.util;

import java.util.Date;

import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.time.FastDateFormat;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class DataWrapper implements DisplaytagColumnDecorator {

	private FastDateFormat dateFormat = FastDateFormat
			.getInstance("dd/MM/yyyy");

	@Override
	public Object decorate(Object columnValue, PageContext pageContext,
			MediaTypeEnum media) throws DecoratorException {
		
		Date data = (Date) columnValue;
		
		return dateFormat.format(data);
	}

}
