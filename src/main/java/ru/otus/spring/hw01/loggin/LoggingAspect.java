package ru.otus.spring.hw01.loggin;

import java.util.Queue;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import ru.otus.spring.hw01.dto.Twit;
import ru.otus.spring.hw01.exception.TwitIdMatchedException;

@Aspect
@Service
public class LoggingAspect {

	@SuppressWarnings("unchecked")
	@Around("execution(java.util.Queue<ru.otus.spring.hw01.dto.Twit> ru.otus.spring.hw01.service.UserAnswersSupplier.get())")
	public Queue<Twit> checkUserInput(ProceedingJoinPoint proceedingJoinPoint) {
		System.err.println(">>> Spring AOP start: checkUserInput\n");
		try {
			Queue<Twit> usersAnswers = (Queue<Twit>) proceedingJoinPoint.proceed();
			Twit firstElement = usersAnswers.peek();

			if (isFirstName(firstElement)) {
				throw new TwitIdMatchedException();
			}
			System.err.println(">>> Spring AOP finished: checkUserInput\n");

			return usersAnswers;

		} catch (Throwable e) {
			throw new TwitIdMatchedException();
		}

	}
	
	private boolean isFirstName(Twit twit) {
		return !twit.getId().equals(-2L);
	}

}
