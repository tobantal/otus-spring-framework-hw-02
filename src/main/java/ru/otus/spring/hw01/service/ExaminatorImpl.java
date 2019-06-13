package ru.otus.spring.hw01.service;

import java.util.Queue;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ru.otus.spring.hw01.dto.Twit;

@Service
public class ExaminatorImpl implements Examinator {

	private final AnswerTester answerTester;
	private final ReportBuilder reportBuilder;
	private final Supplier<Queue<Twit>> userAnswersSupplier;

	public ExaminatorImpl(
			@Qualifier("userAnswersSupplier") Supplier<Queue<Twit>> userAnswersSupplier,
			AnswerTester answerTester,  
			ReportBuilder reportBuilder) {
		this.userAnswersSupplier = userAnswersSupplier;
		this.answerTester = answerTester;
		this.reportBuilder = reportBuilder;
	}

	private Queue<Twit> askQuestions() {
		return userAnswersSupplier.get();
	}

	private String checkAnswers(Queue<Twit> userAnswers) {
		return answerTester.apply(userAnswers);
	}

	private void printResult(String firstName, String lastName, String grade) {
		String report = reportBuilder.buildReport(firstName, lastName, grade);
		System.out.println(report);
	}
	
	@Override
	public void takeAnExam() {
		Queue<Twit> userAnswers = askQuestions();

		String firstName = userAnswers.poll().getText();
		String lastName = userAnswers.poll().getText();
		String grade = checkAnswers(userAnswers);

		printResult(firstName, lastName, grade);
	}

}
