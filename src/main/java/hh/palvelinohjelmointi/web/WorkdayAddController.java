package hh.palvelinohjelmointi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

import java.util.Optional;


import javax.validation.Valid;

import hh.palvelinohjelmointi.domain.UtilityClass;
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

	@RequestMapping (value="/editworkday/{id}")
	public String editWorkday(@PathVariable("id") Long workdayId,Model model){
		
		Optional<Workday> workday = workdayRepository.findById(workdayId);
		if(workday.isPresent()) {
			Workday existingWorkday = workday.get();
			WorkdayAddForm workdayaddform = new WorkdayAddForm(workdayId, existingWorkday.getBeginDate(), 
					existingWorkday.getEndDate(), existingWorkday.getDateAdded(), existingWorkday.getPauligAmount(), existingWorkday.getFazerAmount(), 
					existingWorkday.getMercaAmount(), existingWorkday.getPahvitAmount(), existingWorkday.getAkaaAmount(), existingWorkday.getKeskoAmount(), 
					existingWorkday.getOtherAmount(), existingWorkday.getOtherInfo(), null, existingWorkday.getVehicle());
					model.addAttribute("vehicles", vehicleRepository.findAll());
					model.addAttribute("workdayform", workdayaddform);
					return "editworkday";
		} else {
		    //Kaboom
		}
		return null;

	}  
	
	 @RequestMapping(value = "/addworkday")
	    public String save(@Valid WorkdayAddForm workdayaddform, 
	    		BindingResult bindingResult, RedirectAttributes redirectAttributes) {
	        if (!bindingResult.hasErrors()) { // validation errors
	            Date workdayBegin = workdayaddform.getBeginDate();
	            Date workdayEnd = workdayaddform.getEndDate();

	            if (!UtilityClass.dateIsAfterDate(workdayBegin, workdayEnd)) {
	            	redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.workdayaddform", bindingResult);
	                bindingResult.rejectValue("beginDate", "err.beginDate", "Aloitusaika ei voi olla lopetusajan jälkeen.");
	                redirectAttributes.addFlashAttribute("vehicles", vehicleRepository.findAll());
	                redirectAttributes.addFlashAttribute("workdayaddform", workdayaddform);
	                return "redirect:/addworkday";    
	            }
		    		Workday workday = new Workday();
		    		workday.setId(workdayaddform.getId());
		    		workday.setDateAdded(workdayaddform.getDateAdded());
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
		    		saveWorkday(workday);
	        }
        	else {
        		redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.workdayaddform", bindingResult);
        		redirectAttributes.addFlashAttribute("vehicles", vehicleRepository.findAll());
                redirectAttributes.addFlashAttribute("workdayaddform", workdayaddform);
                return "redirect:/addworkday";    
        	}
        	return "redirect:/workdaylist";    	
        }   
 
	 
	    public String saveWorkday(Workday workday) {
	    	workdayRepository.save(workday);
    	return "redirect:/workdaylist";    	
    }

}
