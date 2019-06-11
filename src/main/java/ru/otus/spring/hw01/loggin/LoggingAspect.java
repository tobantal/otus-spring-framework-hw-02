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
	@Around("execution(java.util.Queue<ru.otus.spring.hw01.dto.Twit> *.*(java.util.Queue<ru.otus.spring.hw01.dto.Twit>))")
	public Queue<Twit> checkTwitId(ProceedingJoinPoint proceedingJoinPoint) {
		System.err.println(">>> Spring AOP start\n");
		Queue<Object> inputQueue = (Queue<Object>) proceedingJoinPoint.getArgs()[0];
		Twit peekIn = (Twit) inputQueue.peek();

		try {
			Queue<Twit> outputQueue = (Queue<Twit>) proceedingJoinPoint.proceed();
			Twit peekOut = outputQueue.peek();

			if (!peekOut.getId().equals(peekIn.getId())) {
				throw new TwitIdMatchedException();
			}
			System.err.println(">>> Spring AOP finished\n");

			return outputQueue;

		} catch (Throwable e) {
			throw new TwitIdMatchedException();
		}

	}

}
