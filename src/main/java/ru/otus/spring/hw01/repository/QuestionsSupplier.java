package ru.otus.spring.hw01.repository;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.dto.Twit;

@Repository("questionsSupplier")
public class QuestionsSupplier implements Supplier<Queue<Twit>> {

	private final Supplier<Queue<Task>> tasksSupplier;
	
	public QuestionsSupplier(Supplier<Queue<Task>> tasksSupplier) {
		this.tasksSupplier = tasksSupplier;
	}

	@Override
	public Queue<Twit> get() {
		Queue<Task> tasks = tasksSupplier.get();
		Queue<Twit> questions = new LinkedList<Twit>();
		Task task;
		while ((task = tasks.poll()) != null) {
			questions.add(new Twit(task.getId(), task.getQuestion()));
		}
		return questions;
	}

}
