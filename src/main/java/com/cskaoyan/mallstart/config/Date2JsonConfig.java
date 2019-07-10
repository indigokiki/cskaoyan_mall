package com.cskaoyan.mallstart.config;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Date2JsonConfig implements Converter<Date,String> {

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public String convert(Date date) {
        String format = sdf.format(date);
        return format;
    }
}
