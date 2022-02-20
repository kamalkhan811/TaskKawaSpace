package com.app.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class ResultsItem{

	@SerializedName("nat")
	private String nat;

	@SerializedName("gender")
	private String gender;

	@SerializedName("name")
	private Name name;

	@SerializedName("location")
	private Location location;

	@SerializedName("email")
	private String email;

	@SerializedName("picture")
	private Picture picture;

	public void setNat(String nat){
		this.nat = nat;
	}

	public String getNat(){
		return nat;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setName(Name name){
		this.name = name;
	}

	public Name getName(){
		return name;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setPicture(Picture picture){
		this.picture = picture;
	}

	public Picture getPicture(){
		return picture;
	}
}