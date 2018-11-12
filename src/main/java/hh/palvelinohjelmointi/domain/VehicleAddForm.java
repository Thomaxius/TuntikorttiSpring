package hh.palvelinohjelmointi.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


public class VehicleAddForm {
	
    public VehicleAddForm(Long id, @NotBlank(message = "Merkki ei voi olla tyhjä.") String make,
			@NotBlank(message = "Malli ei voi olla tyhjä.") String model,
			@Pattern(regexp = "[A-Z]{3}\\-[1-9]{3}", message = "Rekisterinumeron tulee olla muodossa ABC-123") String regNo) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.regNo = regNo;
	}

    public VehicleAddForm() {}
    
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message="Merkki ei voi olla tyhjä.")
	public String make;
    
    @NotBlank(message="Malli ei voi olla tyhjä.")
	public String model;
	
	@Pattern(regexp="[A-Z]{3}\\-[1-9]{3}", message="Rekisterinumeron tulee olla muodossa ABC-123")
	public String regNo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
}