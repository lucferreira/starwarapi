package br.com.desafiob2w.starwarapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class StarwarapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarwarapiApplication.class, args);
	}

}
