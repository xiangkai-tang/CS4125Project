package service;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import region.Region;

@MappedSuperclass
public abstract class Service {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Transient
	protected Region locationFrom;
	
	@Column(name = "locationFrom")
	protected String locationFromString;
	
	@Column(name = "number")
	protected String number;
	
	@Column(name = "cost")
	protected double cost;
	
	/*
	public Service(Region locationFrom, String number)
	{
		this.locationFrom=locationFrom;
		this.locationFromString = locationFrom.toString();
		this.number=number;
	}*/
	public abstract void applyPrice(double price);
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocationFromString() {
		return locationFromString;
	}
	public void setLocationFromString(String locationFromString) {
		this.locationFromString = locationFromString;
	}

	@Transient
	public Region getLocationFrom() {
		return locationFrom;
	}
	public void setLocationFrom(Region locationFrom) {
		this.locationFrom = locationFrom;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
}