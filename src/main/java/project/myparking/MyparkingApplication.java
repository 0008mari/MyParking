package project.myparking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyparkingApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyparkingApplication.class, args);
	}

}
