package ru.otus.spring.hw01.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.otus.spring.hw01.ConfigReportBuilderTest;

@ContextConfiguration(classes = ConfigReportBuilderTest.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Класс ReportBuilder должен ")
public class ReportBuilderTest {
	
	@Autowired
    private LocaleMessageProvider fakeLocaleMessageProvider;
	
	@Autowired
	private ReportBuilder reportBuilder;
	

	@DisplayName("правильно выдавать правильный отчет")
	@Test
	public void shouldBuildReport() {
		String[] args = new String[]{"a", "b", "c"};
		given(fakeLocaleMessageProvider.getMessage("report.template", args)).willReturn("report");
		assertEquals("report", reportBuilder.buildReport(args[0], args[1], args[2]));
	}

}
