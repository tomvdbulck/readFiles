package be.ordina.readfiles;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import be.ordina.readfiles.service.CsvReader;
import be.ordina.readfiles.service.FileReader;
import be.ordina.readfiles.service.PrnReader;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}

	@Bean
	public FileReader csvReader() {
		return new CsvReader("/Workbook2.csv");
	}
	@Bean
	public FileReader prnReader() {
		return new PrnReader("/Workbook2.prn");
	}
}
