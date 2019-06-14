package ru.otus.spring.hw01.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Supplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.otus.spring.hw01.ConfigAnswersAndQuestionsSuppliersTest;
import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.dto.Twit;


@ContextConfiguration(classes = ConfigAnswersAndQuestionsSuppliersTest.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Класс AnswersSupplier должен ")
public class AnswersSupplierTest {
	
	@Autowired
	private Supplier<Queue<Twit>> answersSupplier;
	
	@Autowired
	private Supplier<Queue<Task>> tasksSupplier;
	
	@DisplayName("выводить правильные ответы")
	@Test
	public void shouldGetRightAnswers() {
		Queue<Task> tasks = new LinkedList<>();
		tasks.add(new Task(1L, "question1", "ans1"));
		given(tasksSupplier.get()).willReturn(tasks);
		
		Twit ans = answersSupplier.get().poll();
		assertTrue(ans.getId().equals(1L));
		assertTrue(ans.getText().equals("ans1"));
	}
}
