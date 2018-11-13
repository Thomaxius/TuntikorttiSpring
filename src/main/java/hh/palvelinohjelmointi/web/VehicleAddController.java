package hh.palvelinohjelmointi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import hh.palvelinohjelmointi.domain.Vehicle;
import hh.palvelinohjelmointi.domain.VehicleAddForm;
import hh.palvelinohjelmointi.domain.VehicleRepository;

@Controller
public class VehicleAddController {
	@Autowired
    private VehicleRepository vehicleRepository; 
	
    
	@RequestMapping (value="/editvehicle/{id}")
	public String editWorkday(@PathVariable("id") Long vehicleId, Model model){
		
		Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
		if(vehicle.isPresent()) {
			Vehicle existingVehicle = vehicle.get();
			VehicleAddForm vehicleaddform = new VehicleAddForm(vehicleId, existingVehicle.getVehicleMake(), 
					existingVehicle.getVehicleModel(), existingVehicle.getVehicleRegNo());
					model.addAttribute("vehicles", vehicleRepository.findAll());
					model.addAttribute("vehicleform", vehicleaddform);
					return "editvehicle";
		} else {
		    //Kaboom
		}
		return null;

	}  
	
    @RequestMapping(value = "addvehicle")
    public String addVehicle(Model model){
    	model.addAttribute("vehicleaddform", new VehicleAddForm());
        return "addvehicle";
    }	
    
    @RequestMapping(value = "savevehicle", method = RequestMethod.POST)
    public String saveVehicle(@Valid @ModelAttribute("vehicleaddform") VehicleAddForm vehicleaddform, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) { // validation errors
    		Vehicle vehicle = new Vehicle(vehicleaddform.getId(), vehicleaddform.getMake(), vehicleaddform.getModel() ,vehicleaddform.getRegNo(), new Date());
    		saveVehicle(vehicle);
    	}
    	else {
    		return "addvehicle";
    	}
    	return "redirect:/workdaylist";    	
    }    

    public String saveVehicle(Vehicle vehicle) {
    		vehicleRepository.save(vehicle);
    	return "redirect:/workdaylist";    	
    }
}
