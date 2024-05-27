package by.zhbn.kach;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class KachApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KachApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("admin =" + new BCryptPasswordEncoder().encode("admin"));
		System.out.println("manager =" + new BCryptPasswordEncoder().encode("manager"));
		System.out.println("engineer =" + new BCryptPasswordEncoder().encode("engineer"));
		System.out.println("engineer2 =" + new BCryptPasswordEncoder().encode("engineer2"));
		System.out.println("engineer3 =" + new BCryptPasswordEncoder().encode("engineer3"));
		System.out.println("engineer4 =" + new BCryptPasswordEncoder().encode("engineer4"));
	}
}
