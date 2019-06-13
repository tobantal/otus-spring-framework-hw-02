package ru.otus.spring.hw01;

import java.util.Queue;
import java.util.function.Supplier;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import ru.otus.spring.hw01.dao.CsvDao;
import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.repository.AnswersSupplier;
import ru.otus.spring.hw01.service.LocaleMessageProvider;
import ru.otus.spring.hw01.service.LocaleMessageProviderImpl;

@Import({CsvDao.class, AnswersSupplier.class})
@PropertySource("classpath:application.properties")
@Configuration
public class ConfigAnswersSupplierTest {
	
    @Bean
    public LocaleMessageProvider fakeLocaleMessageProvider() {
        return Mockito.mock(LocaleMessageProviderImpl.class);
    }
    

    @Bean
    public Supplier<Queue<Task>> tasksSupplier() {
    	return Mockito.mock(CsvDao.class);
    }

}