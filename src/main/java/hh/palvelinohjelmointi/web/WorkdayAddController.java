package hh.palvelinohjelmointi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import hh.palvelinohjelmointi.domain.Vehicle;
import hh.palvelinohjelmointi.domain.VehicleRepository;
import hh.palvelinohjelmointi.domain.Workday;
import hh.palvelinohjelmointi.domain.WorkdayAddForm;
import hh.palvelinohjelmointi.domain.WorkdayRepository;

@Controller
public class WorkdayAddController {
	@Autowired
    private WorkdayRepository workdayRepository; 
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
    @RequestMapping(value = "addworkday")
    public String addUser(Model model){
    	model.addAttribute("workdayaddform", new WorkdayAddForm());
    	model.addAttribute("vehicles", vehicleRepository.findAll());
        return "addworkday";
    }	
    
    /**
     * Create new user
     * Check if user already exists & form validation
     * 
     * @param signupForm
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "saveworkday", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("workdayaddform") WorkdayAddForm workdayaddform, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		Workday workday = new Workday();
    		Vehicle vehicle = new Vehicle();
    		workday.setBeginDate(workdayaddform.getBeginDate());
    		workday.setEndDate(workdayaddform.getEndDate());
    		workday.setAkaaAmount(workdayaddform.getAkaaAmount());
    		workday.setPahvitAmount(workdayaddform.getPahvitAmount());
    		workday.setPauligAmount(workdayaddform.getPauligAmount());
    		workday.setFazerAmount(workdayaddform.getFazerAmount());
    		workday.setKeskoAmount(workdayaddform.getKeskoAmount());
    		workday.setMercaAmount(workdayaddform.getMercaAmount());
    		workday.setOtherAmount(workdayaddform.getOtherAmount());
    		workday.setVehicle(workdayaddform.getVehicle());
    		workdayRepository.save(workday);
    	}
    	else {
    		return "addworkday";
    	}
    	return "redirect:/workdaylist";    	
    }    
    
}
