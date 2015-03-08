/*
	Class: Drawer.java
	Author: Andy Yang, John Kang, Stephen Chan, Victor Duan
	Date: January 10, 2014
	School: A.Y. Jackson Secondary School
	Purpose: This class helps to keep track of the ingredients and how much there is left. It will
				also help add and deduct ingredients when supplies are being bought or a dish is cooked  
*/

   public class Drawer {
	
	//Variables for the Drawer class
      private Ingredient storedIngredient;
      private double amountLeft;
   
   //Constructor
      public Drawer (double num, Ingredient ingred) {
         storedIngredient = ingred;
         amountLeft = num;
      }
   
   //Accessor
      public Ingredient getStoredIngredient () {
         return storedIngredient;
      }
      public double getAmountLeft () {
         return amountLeft;
      }
		
   //Mutator
      public void setStoredIngredient (Ingredient sI) {
         storedIngredient = sI;
      }
      public void setAmountLeft (double aL) {
         amountLeft = aL;
      }
   
   //Add more ingredient to the existing drawer
      public boolean addAmount (double amount) {
         amountLeft = amountLeft + amount;
         return true;
      }
   
   //Deduct ingredient from the drawer if there is enough
      public boolean deductAmount (double amount) {
         if ((amountLeft - amount) >= 0) {
            amountLeft = amountLeft - amount;
            return true;
         }
         else {
            return false;
         }
      }
   
	//toString method
   	public String toString(){
   		return (storedIngredient + "Amount Left: "+ amountLeft+ storedIngredient.getUnitMeasurement()+ "\n\n");
   	}
   }