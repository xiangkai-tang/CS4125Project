package service;

/**
 * @author Lucian Epure
 * @author Andre Baraian 10% Hibernate annotations 
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import region.Region;

@Entity
@Table(name = "tb_internetusage")
public class InternetUsage extends Service{

	@Column(name = "speed")
	private double speed;
	
	@Column(name = "traffic")
	private int traffic;
	
	public InternetUsage(Region locationFrom, String number, int traffic) {
		super(locationFrom, number, traffic);
		this.traffic= traffic;
	}
	
	@Override
	public void applyPrice(double roamingTax) {
		
			super.setCost(roamingTax+locationFrom.getInternetPrice()*((float)traffic/10));
			this.international = false;

	}
	@Override
	public void applyCustomerPrice(int leftInternet, double roamingTax) {
		super.setCustomerCost(roamingTax + (leftInternet/10) * this.locationFrom.getInternetPrice());
		 
	}
	
	public int getTraffic() {
		return traffic;
	}
	public void setTraffic(int traffic) {
		this.traffic = traffic;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = "REF-" + referenceNumber;
	}

	@Override
	public String toString() {
		return locationFromString + "," + number + "," + cost + "," + locationFromString + "," + traffic;
	}
	public double getInfo() {
		return this.traffic;
	}

	@Override
	public String changed() {
		// TODO Auto-generated method stub
		return "Internet";
	}

	
	
	
}
