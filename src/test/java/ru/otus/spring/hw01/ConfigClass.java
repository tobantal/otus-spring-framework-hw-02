package ru.otus.spring.hw01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import ru.otus.spring.hw01.service.LocaleMessageProvider;
import ru.otus.spring.hw01.service.LocaleMessageProviderImpl;


@PropertySource("classpath:application.properties")
@Configuration
public class ConfigClass {
	
	@Bean
	public MessageSource testMessageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("/i18n/bundle");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
	
    @Bean
    public LocaleMessageProvider fakeLocaleMessageProvider(
    		@Value("${language}")String language,
    		@Value("${country}") String country) {
		return new LocaleMessageProviderImpl(testMessageSource(), language, country);
    }
    
}