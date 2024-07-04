package cc.converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConverterApplication.class, args);
	}
}