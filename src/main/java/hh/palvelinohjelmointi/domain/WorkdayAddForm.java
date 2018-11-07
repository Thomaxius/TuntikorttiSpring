package hh.palvelinohjelmointi.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WorkdayAddForm {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @PastOrPresent(message="Lopetusaika ei voiolla tulevaisuudessa.") // Checks that Date is either in the past or present
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date beginDate; // Begin date of workday
    
    @PastOrPresent(message="Lopetusaika ei voi olla tulevaisuudessa.") // Checks that Date is either in the past or present
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endDate; // end date of workday
	
    private Date dateAdded = new Date();
	private int pauligAmount;
	private int fazerAmount;
	private int mercaAmount;
	private int pahvitAmount;
	private int akaaAmount;
	private int keskoAmount;
	private int otherAmount;
	private String otherInfo;
	
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employeeid")
	private User employee;
	
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vehicleid")
	private Vehicle vehicle;
    
		
	public Long getId() {
		return id;
	} 
	
	public void setId(Long id) {
		this.id = id;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public int getPauligAmount() {
		return pauligAmount;
	}

	public void setPauligAmount(int pauligAmount) {
		this.pauligAmount = pauligAmount;
	}

	public int getFazerAmount() {
		return fazerAmount;
	}

	public void setFazerAmount(int fazerAmount) {
		this.fazerAmount = fazerAmount;
	}

	public int getMercaAmount() {
		return mercaAmount;
	}

	public void setMercaAmount(int mercaAmount) {
		this.mercaAmount = mercaAmount;
	}

	public int getPahvitAmount() {
		return pahvitAmount;
	}

	public void setPahvitAmount(int pahvitAmount) {
		this.pahvitAmount = pahvitAmount;
	}

	public int getAkaaAmount() {
		return akaaAmount;
	}

	public void setAkaaAmount(int akaaAmount) {
		this.akaaAmount = akaaAmount;
	}

	public int getKeskoAmount() {
		return keskoAmount;
	}

	public void setKeskoAmount(int keskoAmount) {
		this.keskoAmount = keskoAmount;
	}

	public int getOtherAmount() {
		return otherAmount;
	}

	public void setOtherAmount(int otherAmount) {
		this.otherAmount = otherAmount;
	}
	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle ;
	}
    
    
}
