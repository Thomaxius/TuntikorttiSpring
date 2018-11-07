package hh.palvelinohjelmointi;



import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.palvelinohjelmointi.domain.UserRepository;
import hh.palvelinohjelmointi.domain.Vehicle;
import hh.palvelinohjelmointi.domain.User;
import hh.palvelinohjelmointi.domain.WorkdayRepository;
import hh.palvelinohjelmointi.domain.VehicleRepository;
import hh.palvelinohjelmointi.domain.Workday;

@SpringBootApplication
public class TuntikorttiApplication {
	private static final Logger log = LoggerFactory.getLogger(TuntikorttiApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(TuntikorttiApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner workdayDemo(WorkdayRepository workdayRepository, UserRepository urepository, VehicleRepository vehicleRepository) {
		return (args) -> {
			log.info("Saving a couple of workdays and categories..");
			vehicleRepository.save(new Vehicle("Volvo", "FH-16", "ABC-123"));
			vehicleRepository.save(new Vehicle("Volvo", "FH-14", "BCA-123"));
			
			workdayRepository.save(new Workday(new Date(), new Date(), new Date(), 1, 2, 1, 0, 0 , 1, 0, "Otherinfo", null, vehicleRepository.findByMake("Volvo").get(0)));

			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "admin@tuntikortti.com");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "admin@tuntikortti.com");
			urepository.save(user1);
			urepository.save(user2);
			
			
			log.info("Fetching all workdays..");
			for (Workday workday : workdayRepository.findAll()) {
				log.info("workday", workday);
			}

		};
	}
}
