package hh.palvelinohjelmointi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelinohjelmointi.domain.Workday;
import hh.palvelinohjelmointi.domain.WorkdayRepository;
import hh.palvelinohjelmointi.domain.Vehicle;
import hh.palvelinohjelmointi.domain.VehicleRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WorkdaystoreRepositoryTest {
	private static final Logger log = LoggerFactory.getLogger(TuntikorttiApplication.class);
	
    @Autowired
    private WorkdayRepository workdayRepository;

    @Autowired
    private VehicleRepository vehicleRepository;
    
    @Test
    public void findByVehicleMakeReturnsVehicle() {
    	log.info("Running findByVehicleMakeReturnsVehicle test..");
        List<Vehicle> vehicles = vehicleRepository.findByMake("Volvo");
            assertThat(vehicles).hasSize(2);
            assertThat(vehicles.get(0).getVehicleRegNo()).isEqualTo("ABC-123");
        }
    
    @Test
    public void createNewWorkday() {
    	log.info("Running createNewWorkday test");
    	Workday workday = new Workday(new Date(), new Date(), new Date(), 1, 2, 1, 0, 0 , 1, 0, "Otherinfo", null, vehicleRepository.findByMake("Volvo").get(0));
    	workdayRepository.save(workday);
    	assertThat(workdayRepository.findById(workday.getId())).isNotNull();
    }
    
    
    //This probably is a bad thing because we rely on the fact that workdays can be successfully added..
    @Test
    public void deleteWorkday() {
    	log.info("Running deleteVehicle test");
    	Workday workday = new Workday(new Date(), new Date(), new Date(), 1, 2, 1, 0, 0 , 1, 0, "Otherinfo", null, vehicleRepository.findByMake("Volvo").get(0));
		workdayRepository.save(workday);			
    	Optional<Workday> optionalworkdays = workdayRepository.findById(workday.getId());
    	if (optionalworkdays.isPresent()) {
        	workdayRepository.deleteById(optionalworkdays.get().getId());
    	}
    	assertThat(workdayRepository.findById(workday.getId())).isEmpty();
    } 

    
    
}