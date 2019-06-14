package ru.otus.spring.hw01;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.otus.spring.hw01.service.LocaleMessageProvider;
import ru.otus.spring.hw01.service.LocaleMessageProviderImpl;
import ru.otus.spring.hw01.service.ReportBuilder;

@Import(ReportBuilder.class)
@Configuration
public class ConfigReportBuilderTest {
	
    @Bean
    public LocaleMessageProvider fakeLocaleMessageProvider() {
        return Mockito.mock(LocaleMessageProviderImpl.class);
    }

}
