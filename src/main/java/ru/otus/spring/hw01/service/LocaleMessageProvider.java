package ru.otus.spring.hw01.service;

import java.util.Locale;

import org.springframework.lang.Nullable;

public interface LocaleMessageProvider {
	
	String getMessage(String code, @Nullable Object[] args);
	
	Locale getLocale();

}
