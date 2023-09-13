package com.example.demo.entitybean;

public class Address {
	
	
	private String address;
	
	private String city;
	
	private LocationCoordinates coordinates;
	
	private String postalCode;
	
	private String state;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocationCoordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(LocationCoordinates coordinates) {
		this.coordinates = coordinates;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
