package org.owasp.ottawa.topten2017_A8;

import java.io.Serializable;

/**
 * Legit bean
 * 
 * @author Pierre Ernst
 *
 */
public class BicycleBean implements Serializable {

	private static final long serialVersionUID = -4715777557977000345L;

	private String name;
	private int numberOfWheels;
	private int numberOfRiders;

	public BicycleBean() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfWheels() {
		return numberOfWheels;
	}

	public void setNumberOfWheels(int numberOfWheels) {
		this.numberOfWheels = numberOfWheels;
	}

	public int getNumberOfRiders() {
		return numberOfRiders;
	}

	public void setNumberOfRiders(int numberOfRiders) {
		this.numberOfRiders = numberOfRiders;
	}

	@Override
	public String toString() {
		return name + " (" + numberOfRiders + " riders(s), " + numberOfWheels + " wheel(s))";
	}
	
	@Override
	public boolean equals(Object other){
		if (other instanceof BicycleBean){
			BicycleBean otherBicycle = (BicycleBean)other;
			return (this.numberOfRiders==otherBicycle.numberOfRiders)
					&& (this.numberOfWheels==otherBicycle.numberOfWheels)
					&& this.name.equals(otherBicycle.name);
		}
		return false;
	}
}
