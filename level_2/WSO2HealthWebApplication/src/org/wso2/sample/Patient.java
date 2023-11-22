package org.wso2.sample;

public class Patient {
	
	private int patientNumber;
	private String patientLastName;
	private String patientFirstName;
	private String phone;
	private String city;
	private String streetname;
	private String country;
	
	public void setPatientNumber(int patientNum) {
		this.patientNumber = patientNum;
	}
	
	public void setPatientLastName(String lastname) {
		this.patientLastName = lastname;
	}
	
	public void setPatientFirstName(String firstName){
		this.patientFirstName = firstName;
	}
	
	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phone = phoneNum;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public void setStreet(String street) {
		this.streetname = street;
	}
	
	public String getPhone() {
		return phone;
	}

	public String getStreetname() {
		return streetname;
	}

	public int getPatientNumber() {
		return patientNumber;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	

}
