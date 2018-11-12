package hh.palvelinohjelmointi.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long vehicleid;
    
	public String make;
	public String model;
	public String regNo;
	public Date dateAdded;
	
	
	public Vehicle(String Make, String Model, String RegNo, Date dateadded) {
		super();
		this.make = Make;
		this.model = Model;
		this.regNo = RegNo;
		this.dateAdded = dateadded;
	}
	
	public Vehicle() {}

	public Vehicle(Long id, String make, String model, String regno, Date dateadded) {
		super();
		this.vehicleid = id;
		this.make = make;
		this.model = model;
		this.regNo = regno;
		this.dateAdded = dateadded;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "")
	private List<Workday> workdays;
	
	public Long getVehicleid() {
		return vehicleid;
	}


	public void setVehicleid(Long id) {
		this.vehicleid = id;
	}


	public String getVehicleMake() {
		return make;
	}


	public void setVehicleMake(String Make) {
		this.make = Make;
	}


	public String getVehicleModel() {
		return model;
	}


	public void setVehicleModel(String Model) {
		this.model = Model;
	}


	public String getVehicleRegNo() {
		return regNo;
	}


	public void setVehicleRegNo(String RegNo) {
		this.regNo = RegNo;
	}


	public List<Workday> getWorkdays() {
		return workdays;
	}


	public void setWorkdays(List<Workday> workdays) {
		this.workdays = workdays;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + vehicleid + ", Make=" + make + ", Model=" + model
				+ ", RegNo=" + regNo;
	}
}

