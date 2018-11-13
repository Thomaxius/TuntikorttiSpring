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
public class BaseController {
	@Autowired
	private WorkdayRepository workdayRepository;

	@Autowired
	private VehicleRepository vehicleRepository;
	
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
    
    @RequestMapping(value={"", "/", "workdaylist"})
    public String workdayList(Model model) {	
        model.addAttribute("workdays", workdayRepository.findAll());
        return "workdaylist";
    }
    
    @RequestMapping(value="/vehiclelist")
    public String vehicleList(Model model) {	
        model.addAttribute("vehicles", vehicleRepository.findAll());
        return "vehiclelist";
    }      
      // REST
      @RequestMapping(value="/workdays", method = RequestMethod.GET)
      public @ResponseBody List<Workday> workdayListRest() {	
        return (List<Workday>) workdayRepository.findAll();
    }    

      // REST
     @RequestMapping(value="/workday/{id}", method = RequestMethod.GET)
     public @ResponseBody Optional<Workday> findWorkdayRest(@PathVariable("id") Long workdayId) {	
    	return workdayRepository.findById(workdayId);
    }

	
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteWorkday(@PathVariable("id") Long workdayId, Model model) {
    	workdayRepository.deleteById(workdayId);
        return "redirect:../workdaylist";
    }     
}
