package kr.mainstream.seolyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SeolyuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeolyuApplication.class, args);
	}

}
