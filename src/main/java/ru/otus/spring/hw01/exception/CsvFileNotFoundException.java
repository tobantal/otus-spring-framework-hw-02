package ru.otus.spring.hw01.exception;

@SuppressWarnings("serial")
public class CsvFileNotFoundException extends RuntimeException {

	public CsvFileNotFoundException(String msg) {
		super(msg);
	}
	
}
