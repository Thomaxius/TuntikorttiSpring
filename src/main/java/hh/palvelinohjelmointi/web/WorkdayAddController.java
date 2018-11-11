package hh.palvelinohjelmointi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

import javax.validation.Valid;

import hh.palvelinohjelmointi.domain.UtilityClass;
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
	
	@RequestMapping(value = "/addworkday", method = RequestMethod.GET)
	public String addWorkday(Model model){
		  if (!model.containsAttribute("workdayaddform")) {
			    model.addAttribute("vehicles", vehicleRepository.findAll());
			    model.addAttribute("workdayaddform",  new WorkdayAddForm());
			  }
	    return "/addworkday";
	}   
    
	 @RequestMapping(value = "/addworkday", method = RequestMethod.POST)
	    public String save(@Valid WorkdayAddForm workdayaddform, 
	    		BindingResult bindingResult, RedirectAttributes redirectAttributes) {
	        if (!bindingResult.hasErrors()) { // validation errors
	            Date workdayBegin = workdayaddform.getBeginDate();
	            Date workdayEnd = workdayaddform.getEndDate();

	            if (!UtilityClass.dateIsAfterDate(workdayBegin, workdayEnd)) {
	                // Add the vehicle you want to send to the other method.
	            	redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.workdayaddform", bindingResult);
	                bindingResult.rejectValue("beginDate", "err.beginDate", "Aloitusaika ei voi olla lopetusajan jälkeen.");
	                redirectAttributes.addFlashAttribute("vehicles", vehicleRepository.findAll());
	                redirectAttributes.addFlashAttribute("workdayaddform", workdayaddform);
	                return "redirect:/addworkday";    
	            }
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
    		redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.workdayaddform", bindingResult);
    		redirectAttributes.addFlashAttribute("vehicles", vehicleRepository.findAll());
            redirectAttributes.addFlashAttribute("workdayaddform", workdayaddform);
            return "redirect:/addworkday";    
    	}
    	return "redirect:/workdaylist";    	
    }    
    
}
