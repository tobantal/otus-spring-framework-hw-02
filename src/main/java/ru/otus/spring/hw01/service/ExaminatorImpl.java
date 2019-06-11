package ru.otus.spring.hw01.service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ru.otus.spring.hw01.dto.Twit;

@Service
public class ExaminatorImpl implements Examinator {

	private final Supplier<Queue<Twit>> questionsSupplier;
	private final AnswerTester answerTester;
	private final Interviewer interviewer;
	private final ReportBuilder reportBuilder;
	private final LocaleMessageProvider localeMessageProvider;

	public ExaminatorImpl(@Qualifier("questionsSupplier") Supplier<Queue<Twit>> questionsSupplier,
			AnswerTester answerTester, Interviewer interviewer, ReportBuilder reportBuilder,
			LocaleMessageProvider localeMessageProvider) {
		this.questionsSupplier = questionsSupplier;
		this.answerTester = answerTester;
		this.interviewer = interviewer;
		this.reportBuilder = reportBuilder;
		this.localeMessageProvider = localeMessageProvider;
	}

	private Queue<Twit> askQuestions() {
		Queue<Twit> questions = new LinkedList<Twit>();
		Twit firstNameTwit = new Twit(-2L, localeMessageProvider.getMessage("question.firstname", null));
		Twit lastNameTwit = new Twit(-1L, localeMessageProvider.getMessage("question.lastname", null));
		questions.add(firstNameTwit);
		questions.add(lastNameTwit);
		questions.addAll(questionsSupplier.get());
		return interviewer.apply(questions);
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
