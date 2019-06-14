package ru.otus.spring.hw01;

import java.util.Queue;
import java.util.function.Supplier;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.spring.hw01.dao.CsvDao;
import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.repository.AnswersSupplier;
import ru.otus.spring.hw01.repository.QuestionsSupplier;

@Import({AnswersSupplier.class, QuestionsSupplier.class})
@Configuration
public class ConfigAnswersAndQuestionsSuppliersTest {

    @Bean
    public Supplier<Queue<Task>> tasksSupplier() {
    	return Mockito.mock(CsvDao.class);
    }

}