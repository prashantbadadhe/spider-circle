package com.circle.model.organization;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Location {

	 
	private String locationType;
	private String locationValue;
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(String locationValue) {
		this.locationValue = locationValue;
	}
	
	
}
