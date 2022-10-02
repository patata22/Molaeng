package idle.molaeng_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableScheduling
public class MolaengBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MolaengBackApplication.class, args);
	}




}
