package hh.palvelinohjelmointi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelinohjelmointi.web.BaseController;
import hh.palvelinohjelmointi.web.UserController;
import hh.palvelinohjelmointi.web.VehicleAddController;
import hh.palvelinohjelmointi.web.WorkdayAddController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TuntikorttiApplicationTests {

	@Autowired
	private BaseController basecontroller;
	
	@Autowired
	private UserController usercontroller;
	
	@Autowired
	private VehicleAddController vehicleaddcontroller;
	
	@Autowired
	private WorkdayAddController workdayaddcontroller;
	
	@Test
	public void contextLoads() throws Exception {
	assertThat(basecontroller).isNotNull();
	assertThat(usercontroller).isNotNull();
	assertThat(vehicleaddcontroller).isNotNull();
	assertThat(workdayaddcontroller).isNotNull();
	}
	}
