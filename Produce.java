/*
	Class: Produce.java
	Author: Andy Yang, John Kang, Stephen Chan, Victor Duan
	Date: January 10, 2014
	School: A.Y. Jackson Secondary School
	Purpose: This is a subclass used to create a produce object.
*/

public class Produce {
	private Ingredient ingred;
	private double unitCost;
	
	//Constructor
	public Produce(Ingredient ingred, double unitCost) {
		this.ingred = ingred;
		this.unitCost = unitCost;
	}
	
	//Accessor
	public Ingredient getIngred() {
		return ingred;
	}
	
	public double getUnitCost() {
		return unitCost;
	}
	//Mutator
	public void setIngred(Ingredient ingred) {
		this.ingred = ingred;
	}
	
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	
	//toString method to output the fields in an orderly fashion
	public String toString() {
		String produceInfo = ingred.getName() + " unit cost: " + unitCost + "$/" + ingred.getUnitMeasurement();
		return produceInfo;
	}
}