package be.ordina.readfiles;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import be.ordina.readfiles.service.CsvReader;
import be.ordina.readfiles.service.FileReader;
import be.ordina.readfiles.service.PrnReader;

@SpringBootApplication
@ComponentScan("be.ordina.readfiles")
@EnableConfigurationProperties
public class Application {

	@Value("${csv.file.name}")
	private String csvFile;

	@Value("${prn.file.name}")
	private String prnFile;

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
		return new CsvReader(csvFile);
	}

	@Bean
	public FileReader prnReader() {
		return new PrnReader(prnFile);
	}
}
