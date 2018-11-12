package hh.palvelinohjelmointi.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class SignupForm {
	 @NotBlank(message="Anna käyttäjätunnus.")
    @Size(min=5, message="Salasanan tulee olla vähintään 5 merkkiä pitkä.")
    private String username = "";

    @NotBlank(message="Anna salasana.")
    @Size(min=7, message="Salasanan tulee olla vähintään 7 merkkiä pitkä.")
    private String password = "";

    @NotBlank(message="Anna salasanavahvistus.")
    @Size(min=7, message="Salasanan tulee olla vähintään 7 merkkiä pitkä.")
    private String passwordCheck = "";

    @NotBlank(message="Kenttä ei saa olla tyhjä.")
    private String role = "USER";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
    
}
