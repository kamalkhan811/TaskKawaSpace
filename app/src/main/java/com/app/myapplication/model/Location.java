package com.app.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Location{

	@SerializedName("country")
	private String country;

	@SerializedName("city")
	private String city;

	@SerializedName("street")
	private Street street;

	@SerializedName("timezone")
	private Timezone timezone;

	@SerializedName("postcode")
	private String postcode;

	@SerializedName("coordinates")
	private Coordinates coordinates;

	@SerializedName("state")
	private String state;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setStreet(Street street){
		this.street = street;
	}

	public Street getStreet(){
		return street;
	}

	public void setTimezone(Timezone timezone){
		this.timezone = timezone;
	}

	public Timezone getTimezone(){
		return timezone;
	}

	public void setPostcode(String postcode){
		this.postcode = postcode;
	}

	public String getPostcode(){
		return postcode;
	}

	public void setCoordinates(Coordinates coordinates){
		this.coordinates = coordinates;
	}

	public Coordinates getCoordinates(){
		return coordinates;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}
}