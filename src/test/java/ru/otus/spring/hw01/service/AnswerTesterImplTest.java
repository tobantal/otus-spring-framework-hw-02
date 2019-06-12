package ru.otus.spring.hw01.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.otus.spring.hw01.dto.Twit;
import ru.otus.spring.hw01.exception.TwitIdMatchedException;


@DisplayName("Класс AnswerTesterImpl должен ")
public class AnswerTesterImplTest {

	// mock(userAnswers).get() willReturn(new Twit(1L, "a"))
	
	private AnswerTester answerTester; // замокать AnswerSupplier
	private Queue<Twit> userAnswers;
	private Queue<Twit> rightAnswers;

	@BeforeEach
	public void setUp() {
		rightAnswers = new LinkedList<Twit>();
		userAnswers = new LinkedList<Twit>();
	}

	@DisplayName("выдавать 1 при правильном ответе")
	@Test
	public void check_right_answer() {
		rightAnswers.add(new Twit(1L, "a"));
		userAnswers.add(new Twit(1L, "a"));
		answerTester = new AnswerTesterImpl(() -> rightAnswers);
		assertEquals("1", answerTester.apply(userAnswers));
	}
	
	@DisplayName("выдавать 1 при правильном ответе без учета регистра")
	@Test
	public void check_ignore_case() {
		rightAnswers.add(new Twit(1L, "a"));
		userAnswers.add(new Twit(1L, "A"));
		answerTester = new AnswerTesterImpl(() -> rightAnswers);
		// mock(userAnswers).get() willReturn(new Twit(1L, "a"))
		// 
		assertEquals("1", answerTester.apply(userAnswers));
	}

	@DisplayName("выдавать 0 при неправильном ответе")
	@Test
	public void check_wrong_answer() {
		rightAnswers.add(new Twit(1L, "a"));
		userAnswers.add(new Twit(1L, "x"));
		answerTester = new AnswerTesterImpl(() -> rightAnswers);
		assertEquals("0", answerTester.apply(userAnswers));
	}

	@DisplayName("выбрасывать исключение TwitIdMatchedException при не равных id")
	@Test
	public void check_not_equals_id() {
		rightAnswers.add(new Twit(1L, "a"));
		userAnswers.add(new Twit(2L, "a"));
		answerTester = new AnswerTesterImpl(() -> rightAnswers);
		assertThrows(TwitIdMatchedException.class, () -> {
			answerTester.apply(userAnswers);
		  });
	}

	@DisplayName("правиль считать тоговую оценку")
	@Test
	public void check_total_count() {
		rightAnswers.add(new Twit(1L, "a"));
		rightAnswers.add(new Twit(2L, "b"));
		rightAnswers.add(new Twit(3L, "c"));
		rightAnswers.add(new Twit(4L, "d"));
		rightAnswers.add(new Twit(5L, "e"));
		
		userAnswers.add(new Twit(1L, "a"));
		userAnswers.add(new Twit(2L, "x"));
		userAnswers.add(new Twit(3L, "c"));
		userAnswers.add(new Twit(4L, "y"));
		userAnswers.add(new Twit(5L, "e"));
		answerTester = new AnswerTesterImpl(() -> rightAnswers);
		assertEquals("3", answerTester.apply(userAnswers));
	}

}
