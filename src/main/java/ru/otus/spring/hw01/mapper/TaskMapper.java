package ru.otus.spring.hw01.mapper;

import java.util.Queue;

import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.dto.Twit;

public interface TaskMapper {
	
	Queue<Twit> tasks2questions(Queue<Task> tasks);
	
	Queue<Twit> tasks2answers(Queue<Task> tasks);
}
