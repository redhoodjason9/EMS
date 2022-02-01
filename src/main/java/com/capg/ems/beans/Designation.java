package com.capg.ems.beans;

public enum Designation {
	SOFTWARE_ENGINEER("Software Engineer"),
	SENIOR_SOFTWARE_ENGINEER("Senior Software Engineer"),
	ASSOCIATE_CONSULTANT("Associate Consultant"),
	CONSULTANT("Consultant"),
	SENIOR_CONSULTANT("Senior Consultant");

	private String name;
	
	Designation(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
}
