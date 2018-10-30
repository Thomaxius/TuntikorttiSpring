package hh.palvelinohjelmointi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long employeeid;
    private String name;
    private String phoneNo;

	public Employee() {}
    
	public Employee(Long employeeid, String name, String phoneNo) {
		super();
		this.employeeid = employeeid;
		this.name = name;
		this.phoneNo = phoneNo;
	}
	public Long getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(Long employeeid) {
		this.employeeid = employeeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
    
    @Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", name=" + name + ", phoneNo=" + phoneNo + "]";
	}

}
