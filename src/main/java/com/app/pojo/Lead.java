package com.app.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lead {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leadId;

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String firstName;

	@Pattern(regexp = "^[a-zA-Z]*$")
	private String middleName;

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String lastName;

	@NotBlank
	@Pattern(regexp = "^[6-9][0-9]{9}$")
	private String mobileNumber;

	@NotBlank
	@Pattern(regexp = "^(Male|Female|Others)$")
	private String gender;

	@NotNull
	@Past
	private LocalDate dob;

	@Email
	@NotBlank
	private String email;

	public Long getLeadId() {
		return leadId;
	}

	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
