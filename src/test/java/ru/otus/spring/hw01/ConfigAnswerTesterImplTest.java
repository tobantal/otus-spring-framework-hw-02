package ru.otus.spring.hw01;

import java.util.Queue;
import java.util.function.Supplier;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import ru.otus.spring.hw01.dto.Twit;
import ru.otus.spring.hw01.repository.AnswersSupplier;
import ru.otus.spring.hw01.service.AnswerTesterImpl;

@Import(AnswerTesterImpl.class)
@PropertySource("classpath:application.properties")
@Configuration
public class ConfigAnswerTesterImplTest {
	
    @Bean
    public Supplier<Queue<Twit>> answersSupplier() {
    	return Mockito.mock(AnswersSupplier.class);
    }

}