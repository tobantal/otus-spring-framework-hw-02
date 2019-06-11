package ru.otus.spring.hw01.dao;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Queue;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.otus.spring.hw01.ConfigClass;
import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.exception.ColumnNumberException;
import ru.otus.spring.hw01.exception.CsvFileNotFoundException;
import ru.otus.spring.hw01.service.LocaleMessageProvider;
import ru.otus.spring.hw01.service.LocaleMessageProviderImpl;

@ContextConfiguration(classes = ConfigClass.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Класс CsvDao должен ")
class CsvDaoTest {
    
    @Value("${questions.limit}")
    private String limit;
    
    @Autowired
    @Qualifier("testMessageSource")
    MessageSource messageSource;
    
    @Autowired
    LocaleMessageProvider fakeLocaleMessageProvider;

    @Test
    @DisplayName("выдавать корректные задачи из корректного csv-файла")
    void correct_loading_of_the_file() {
        TaskDao taskDao = new CsvDao(fakeLocaleMessageProvider);
        Queue<Task> queue = taskDao.get();
        BiPredicate<Task, Integer> p = (t, i) -> t.getId() == i && t.getQuestion().equals("Task" + i) && t.getAnswer().equals("Answer" + i);
        assertTrue(IntStream.rangeClosed(1, 5).allMatch(i -> p.test(queue.poll(), i)));
        assertNull(queue.poll());
    }
    
    @Test
    @DisplayName("выбрасывать ColumnNumberException при чтении csv-файла со столбцами длины < 3")
    void shouldThrowColumnNumberException() {
    	LocaleMessageProvider providerThrowsException = new LocaleMessageProviderImpl(messageSource, "ex", "ColumnNumberException");
        TaskDao taskDao = new CsvDao(providerThrowsException);
        assertThatThrownBy(taskDao::get).isInstanceOf(ColumnNumberException.class);
    }
    
    @Test
    @DisplayName("выбрасывать NullPointerException при чтении csv-файла со столбцами == null")
    void shouldThrowNullPointerException() {
    	LocaleMessageProvider providerThrowsException = new LocaleMessageProviderImpl(messageSource, "ex", "NullPointerException");
        TaskDao taskDao = new CsvDao(providerThrowsException);
        assertThatThrownBy(taskDao::get).isInstanceOf(NullPointerException.class);
    }
    
    @Test
    @DisplayName("выбрасывать NumberFormatException при чтении csv-файла с нечисловым id")
    void shouldThrowNumberFormatException() {
    	LocaleMessageProvider providerThrowsException = new LocaleMessageProviderImpl(messageSource, "ex", "NumberFormatException");
        TaskDao taskDao = new CsvDao(providerThrowsException);
        assertThatThrownBy(taskDao::get).isInstanceOf(NumberFormatException.class);
    }
    
    @Test
    @DisplayName("выбрасывать CsvFileNotFoundException, если csv-файла не существует")
    void shouldThrowFileNotFoundException() {
    	LocaleMessageProvider providerThrowsException = new LocaleMessageProviderImpl(messageSource, "ex", "CsvFileNotFoundException");
        TaskDao taskDao = new CsvDao(providerThrowsException);
        assertThatThrownBy(taskDao::get).isInstanceOf(CsvFileNotFoundException.class);
    }
    
    @Test
    @DisplayName("не выбрасывать NoSuchMessageException, если нет поля csvfile в properties")
    void shouldThrowNoSuchMessageException() {
    	LocaleMessageProvider providerThrowsException = new LocaleMessageProviderImpl(messageSource, "ex", "NoSuchMessageException");
    	TaskDao taskDao = new CsvDao(providerThrowsException);
    	assertThatCode(taskDao::get).doesNotThrowAnyException();
    }
    
    
    
    
}
