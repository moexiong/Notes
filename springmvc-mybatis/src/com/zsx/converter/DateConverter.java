package com.zsx.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

//需要实现核心包下的转换包的转换类
public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd HH/mm/ss");
		try {
			if (source != null) {
				Date parse = df.parse(source);
				return parse;
			}
		} catch (ParseException e) {}
		return null;
	}

}
