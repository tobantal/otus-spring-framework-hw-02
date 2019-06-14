package ru.otus.spring.hw01.service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ru.otus.spring.hw01.dto.Twit;

@Service("userAnswersSupplier")
public class UserAnswersSupplier implements Supplier<Queue<Twit>> {

	private final Supplier<Queue<Twit>> questionsSupplier;
	private final LocaleMessageProvider localeMessageProvider;

	public UserAnswersSupplier(
			@Qualifier("questionsSupplier") Supplier<Queue<Twit>> questionsSupplier,
			LocaleMessageProvider localeMessageProvider) {
		this.questionsSupplier = questionsSupplier;
		this.localeMessageProvider = localeMessageProvider;
	}

	@Override
	public Queue<Twit> get() {
		try (Scanner scanner = new Scanner(System.in)) {
			Queue<Twit> answers = new LinkedList<Twit>();
			Queue<Twit> questions = new LinkedList<Twit>();
			Twit firstNameTwit = new Twit(-2L, localeMessageProvider.getMessage("question.firstname", null));
			Twit lastNameTwit = new Twit(-1L, localeMessageProvider.getMessage("question.lastname", null));
			questions.add(firstNameTwit);
			questions.add(lastNameTwit);
			questions.addAll(questionsSupplier.get());
			questions.forEach(questionTwit -> {
				System.out.println(questionTwit.getText());
				String answerText = scanner.next();
				answers.add(new Twit(questionTwit.getId(), answerText));
			});
			return answers;
		}
	}

}
