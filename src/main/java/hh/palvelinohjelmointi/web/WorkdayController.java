package hh.palvelinohjelmointi.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.palvelinohjelmointi.domain.VehicleRepository;
import hh.palvelinohjelmointi.domain.Workday;
import hh.palvelinohjelmointi.domain.WorkdayRepository;

@Controller
public class WorkdayController {
	@Autowired
	private WorkdayRepository workdayRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	
	// Login page
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
    @RequestMapping(value="/workdaylist")
    public String workdayList(Model model) {	
        model.addAttribute("workdays", workdayRepository.findAll());
        return "workdaylist";
    }
  
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("workday", new Workday());
    	model.addAttribute("vehicles", vehicleRepository.findAll());
        return "addworkday";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(Workday workday){
    	workdayRepository.save(workday);
        return "redirect:workdaylist";
    }
    
      // REST
      @RequestMapping(value="/workdays", method = RequestMethod.GET)
      public @ResponseBody List<Workday> workdayListRest() {	
        return (List<Workday>) workdayRepository.findAll();
    }    

      // REST
     @RequestMapping(value="/workday/{id}", method = RequestMethod.GET)
     public @ResponseBody Optional<Workday> findBookRest(@PathVariable("id") Long workdayId) {	
    	return workdayRepository.findById(workdayId);
    }
      
	@RequestMapping (value="/edit/{id}")
	public String editBook(@PathVariable("id") Long workdayId,Model model){
		model.addAttribute("workday", workdayRepository.findById(workdayId));
		return "editworkday";
	}
	
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long workdayId, Model model) {
    	workdayRepository.deleteById(workdayId);
        return "redirect:../workdaylist";
    }     
}
