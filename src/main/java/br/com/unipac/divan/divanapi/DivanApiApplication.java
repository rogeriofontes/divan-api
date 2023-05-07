package br.com.unipac.divan.divanapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Projeto Divan API", version = "1.0", description = "Employees Information"))
public class DivanApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DivanApiApplication.class, args);
	}

}
