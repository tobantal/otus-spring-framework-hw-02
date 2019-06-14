package ru.otus.spring.hw01;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import ru.otus.spring.hw01.service.LocaleMessageProviderImpl;

@Import(LocaleMessageProviderImpl.class)
@PropertySource("classpath:application.properties")
@Configuration
public class ConfigLocaleMessageProviderImplTest {
	
	@Bean
	public MessageSource messageSource() {
		return Mockito.mock(MessageSource.class);
	}

}
